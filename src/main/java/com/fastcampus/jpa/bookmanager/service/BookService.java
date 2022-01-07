package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Author;
import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.repository.AuthorRepository;
import com.fastcampus.jpa.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
//@Transactional // 클래스에도 트랙잭션 사용 가능, 클래스 내 각각 메소드에 대한 트랙잭션 설정임, method scope이 선적용됨
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;
    private final AuthorService authorService;

    @Transactional(propagation = Propagation.REQUIRED)
//    @Transactional(rollbackFor = Exception.class)   // rollbackFor 옵션으로 checked Exception 도 롤백처리 가능
//    public void putBookAndAuthor() {
    public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        try {
            authorService.putAuthor();
        } catch (RuntimeException e) {
        }

        throw new RuntimeException("오류가 발생하였습니다. transaction 은 어떻게 될까요?");

//        Author author = new Author();
//        author.setName("martin");
//
//        authorRepository.save(author);

        // 트랜잭션 내에서
        // unchecked exception 은 롤백되나, checked exception 은 롤백되지 않고 반영됨
//        throw new RuntimeException("오류가 나서 DB commit 이 발생하지 않습니다");   // 롤백됨
//        throw new Exception("오류가 나서 DB commit 이 발생하지 않습니다");    // 롤백안됨
    }

    // 스프링 컨테이너는 Bean 으로 진입할 때, 걸려있는 @Transactional 어노테이션에 대해 처리하게 되어있음
    // 따라서, Bean 진입 후에 @Transactional 어노테이션 걸린 내부 메소드를 호출하는 경우 트랜잭션 처리되지 않음
//    public void put() {
//        this.putBookAndAuthor();    // 같은 클래스 내에서 메소드 호출
//    }


//    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
//    @Transactional(isolation = Isolation.READ_COMMITTED)
//    @Transactional(isolation = Isolation.REPEATABLE_READ) // phantom read 상태 발생할 수 있음 (데이터 안보이는데 업데이트 되거나 하는 경우)
    @Transactional(isolation = Isolation.SERIALIZABLE)  // commit 이 일어나지 않은 트랙잭션이 존재하게 되면, lock 을 통해 waiting 하게 됨
    public void get(Long id) {
        System.out.println(">>> " + bookRepository.findById(id).get());
        System.out.println(">>> " + bookRepository.findAll());

        entityManager.clear();

        System.out.println(">>> " + bookRepository.findById(id).get()); // id로 조회하는 경우, Entity cache 에 의해 값을 가져오게 됨, unrepeatable read 상태, entityManager.clear(); 사용해서 처리?
        System.out.println(">>> " + bookRepository.findAll());

        bookRepository.update();

        entityManager.clear();

//        Book book = bookRepository.findById(id).get();
//        book.setName("바뀔까?");
//        bookRepository.save(book);
    }

    @Transactional
    public List<Book> getAll() {
        List<Book> books = bookRepository.findAll();

        books.forEach(System.out::println);

        return books;
    }

}
