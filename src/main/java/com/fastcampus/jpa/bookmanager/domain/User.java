package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import com.fastcampus.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@Builder
@Entity
//@EntityListeners(value = {MyEntityListener.class, UserEntityListener.class})
//@EntityListeners(value = {AuditingEntityListener.class, UserEntityListener.class}) // JPA 에서 AuditingEntityListener 기본적으로 제공함
@EntityListeners(value = {UserEntityListener.class})
//@Table(name = "user", indexes = { @Index(columnList = "name")}, uniqueConstraints = { @UniqueConstraint(columnNames = "email")})
//public class User extends BaseEntity implements Auditable {
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
//    @Column(unique = true)
    private String email;

    @Enumerated(value = EnumType.STRING) // Enum 사용하는 경우 EnumType.STRING 으로 사용할 것!
    private Gender gender;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "home_city")),
            @AttributeOverride(name = "district", column = @Column(name = "home_district")),
            @AttributeOverride(name = "detail", column = @Column(name = "home_address_detail")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "home_zip_code"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "company_city")),
            @AttributeOverride(name = "district", column = @Column(name = "company_district")),
            @AttributeOverride(name = "detail", column = @Column(name = "company_address_detail")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "company_zip_code"))
    })
    private Address companyAddress;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ToString.Exclude
    private List<UserHistory> userHistories = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

//    @Column
//    @Column(name = "crtdat")
//    @Column(nullable = false)
//    @Column(updatable = false)
//    @CreatedDate
//    private LocalDateTime createdAt;
//
////    @Column(insertable = false)
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

//    @Transient // 영속성 처리에서 제외되기 때문에, DB 데이터에 반영되지 않음
//    private String testData;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;

//    @PrePersist // persist, 즉 insert 메서드가 실행되기전에 실행됨
//    public void prePersist() {
////        System.out.println(">>> prePersist");
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
////        System.out.println(">>> preUpdate");
//        this.updatedAt = LocalDateTime.now();
//    }

//    @PostPersist
//    public void postPersist() {
//        System.out.println(">>> postPersist");
//    }
//
//    @PostUpdate
//    public void postUpdate() {
//        System.out.println(">>> postUpdate");
//    }
//
//    @PreRemove
//    public void preRemove() {
//        System.out.println(">>> preRemove");
//    }
//
//    @PostRemove
//    public void postRemove() {
//        System.out.println(">>> postRemove");
//    }
//
//    @PostLoad // select 조회가 일어난 직후에 실행되는 메서드
//    public void postLoad() {
//        System.out.println(">>> postLoad");
//    }
}
