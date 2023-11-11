//package com.dashboard.gmelan.user.Entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.sql.Timestamp;
//
//@Entity(name="jwt_token")
//@Getter
//@Setter
//public class JWTTokenEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name="token", columnDefinition = "varchar(255) not null unique")
//    private String token;
//
//    @Column(name="expire_at", columnDefinition = "datetime not null")
//    private Timestamp expire_at;
//
//    // mapped column: user_id
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity user;
//}
