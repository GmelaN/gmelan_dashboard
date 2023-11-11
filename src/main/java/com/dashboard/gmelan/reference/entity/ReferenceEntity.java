//package com.dashboard.gmelan.reference.entity;
//
//import com.dashboard.gmelan.user.Entity.UserEntity;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.sql.Timestamp;
//
//@Entity(name="reference")
//@Getter
//@Setter
//public class ReferenceEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name="title", nullable = false, columnDefinition = "varchar(30) default '제목'")
//    private String title;
//
//    @Column(name="content", columnDefinition = "text default '내용 없음'")
//    private String content;
//
//    @Column(name="url", columnDefinition = "varchar(50)")
//    private String url;
//
//    @Column(name="created_at", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
//    private Timestamp created_at;
//
//    @Column(name="updated_at", columnDefinition = "datetime")
//    private Timestamp updated_at;
//
//    // mapped columns: user, reference_category
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity user;
//
//    @ManyToOne
//    @JoinColumn(name = "reference_category_id")
//    private ReferenceCategoryEntity category;
//
//}
