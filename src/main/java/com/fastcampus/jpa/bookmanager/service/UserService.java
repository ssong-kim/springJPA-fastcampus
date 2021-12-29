package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class UserService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void put() {

        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@fastcampus.com");
        // >>> 비영속 상태

//        userRepository.save(user); // userRepository 사용해도 영속화 처리됨

        entityManager.persist(user);      // 영속화해서 user entity 를 managed 상태로 만듬
//        entityManager.detach(user);     // 준영속 상태 (detached), 영속성 컨텍스트에서 user entity 를 더 이상 관리하지 않게 됨

        user.setName("newUserAfterPersist");
//        userRepository.save(user);    // 영속성 컨텍스트 내에서 관리되는 entity 는, save 메소드 실행안해도 트랜잭션 종료시점에 업데이트 내용 반영됨
        entityManager.merge(user);  // 준영속 상태의 entity 도 명시적으로 merge() 호출하면 데이터 반영 일어남

//        entityManager.flush();
//        entityManager.clear();  // clear() 하기전에, 예약된 변경내역들 적용을 위해 flush() 할 것을 권고

        User user1 = userRepository.findById(1L).get();
        entityManager.remove(user1);

        user1.setName("marrrrrrrrtin");
//        entityManager.merge(user1);
    }
}
