package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Author;
import com.fastcampus.jpa.bookmanager.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional(propagation = Propagation.REQUIRED)    // 호출하는 쪽의 트랜잭션이 있다면 사용, 없으면 새로 생성 // default 값
//    @Transactional(propagation = Propagation.REQUIRES_NEW)    // 트랙잭션 별도로 생성 (완전히 독립되어있음)
//    @Transactional(propagation = Propagation.NESTED)    // 호출하는 쪽의 트랜잭션을 그대로 활욯하면서 독립적으로, 상위에 영향주지 않음 // NESTED 설정해서 사용할 수는 있으나, jpa에서 사용하지 않는다 정도로 보면 됨
//    @Transactional(propagation = Propagation.SUPPORTS)    // 호출하는 쪽의 트랜잭션이 있다면 사용, 없으면 트랙잭션 새로 생성하지 않음
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)    // 호출하는 쪽의 트랜잭션이 있다면 사용, 없으면 트랙잭션 없이 동작하도록 별개로 설정, 호출받는쪽의 실행이 완료된 이후에 호출하는쪽 처리
//    @Transactional(propagation = Propagation.MANDATORY)    // 호출하는 쪽에 필수적으로 트랙잭션 존재해야함, 없으면 오류 발생시킴
//    @Transactional(propagation = Propagation.NEVER)    // 호출하는 쪽에 필수적으로 트랙잭션 없어야 함, 있으면 오류 발생시킴
    public void putAuthor() {
        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);

//        throw new RuntimeException("오류가 발생하였습니다. transaction 은 어떻게 될까요?");
    }
}
