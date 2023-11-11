//package com.dashboard.gmelan.reference.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.sql.Timestamp;
//
//@Getter
//@Setter
//@Entity(name="reference-category")
//public class ReferenceCategoryEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name="name", nullable = false, unique = true, columnDefinition = "varchar(20)")
//    private String name;
//
//    @Column(name="created_at", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
//    private Timestamp created_at;
//}
