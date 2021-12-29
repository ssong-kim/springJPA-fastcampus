package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    //    List<User> findByName(String name);
//    Optional<User> findByName(String name);
    Set<User> findByName(String name);

    Set<User> findUserByNameIs(String name);

    Set<User> findUserByName(String name);

    Set<User> findUserByNameEquals(String name);

    User findByEmail(String email);

    User getByEmail(String email);

    User readByEmail(String email);

    User queryByEmail(String email);

    User searchByEmail(String email);

    User streamByEmail(String email);

    User findUserByEmail(String email);

    User findSomethingByEmail(String email);

    List<User> findFirst1ByName(String name);

    List<User> findTop2ByName(String name);

    List<User> findLast1ByName(String name); // Last 키워드는 제공되지 않는 키워드로, Last1은 무시됨

    List<User> findByEmailAndName(String email, String name);

    List<User> findByEmailOrName(String email, String name);

    List<User> findByCreatedAtAfter(LocalDateTime yesterday);

    List<User> findByIdAfter(Long id); // 숫자도 비교 가능하나, 가독성을 위해 날짜비교에 사용 권장

    List<User> findByCreatedAtGreaterThan(LocalDateTime yesterday);

    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);

    List<User> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);

    List<User> findByIdBetween(Long id1, Long id2);

    List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);

    List<User> findByIdIsNotNull();

    List<User> findByIdIsNull();

//    List<User> findByAddressIsNotEmpty();

    List<User> findByNameIn(List<String> names);

    List<User> findByNameStartingWith(String name);

    List<User> findByNameEndingWith(String name);

    List<User> findByNameContains(String name);

    List<User> findByNameLike(String name);

    List<User> findTop1ByName(String name);

    List<User> findTop1ByNameOrderByIdDesc(String name);

    List<User> findTopByNameOrderByIdDesc(String name); // Top 뒤 숫자 없으면 default 1

    List<User> findFirstByNameOrderByIdDescEmailAsc(String name);

    List<User> findFirstByName(String name, Sort sort);

    Page<User> findByName(String name, Pageable pageable);

    @Query(value = "select * from user limit 1;", nativeQuery = true)
    Map<String, Object> findRowRecord();
}
