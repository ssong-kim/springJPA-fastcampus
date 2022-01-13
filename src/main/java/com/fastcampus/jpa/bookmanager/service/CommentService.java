package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Comment;
import com.fastcampus.jpa.bookmanager.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void init() {
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setComment("최고예요");

            commentRepository.save(comment);
        }
    }

//    @Transactional
    @Transactional(readOnly = true)
    public void updateSomething() {
        List<Comment> comments = commentRepository.findAll();

        for (Comment comment : comments) {
            comment.setComment("별로에요");

            // @Transactional 어노테이션 있으면 save() 하지 않아도, 영속성 컨텍스트에서 dirty check 하여 변경사항 저장함
//            commentRepository.save(comment);
        }
    }

    @Transactional
    public void insertSomething() {
//        Comment comment = new Comment();
//        comment.setComment("이건뭐죠?");
//        // 영속화되어있지 않기 때문에, dirty check 일어나지 않고,
//        // 때문에 save() 없으면 DB에 영속화하는 과정이 일어나지 않음 (DB 저장 x)
//
//        commentRepository.save(comment);    // entity 영속화 시키겠다고 명시적으로 표시해줘야만 insert 처리됨

        Comment comment = commentRepository.findById(1L).get();
        comment.setComment("이건뭐죠?");    // save 명령어 날리지 않아도, 영속성 컨텍스트에서 관리하는 데이터이기 떄문에 update 됨
    }
}
