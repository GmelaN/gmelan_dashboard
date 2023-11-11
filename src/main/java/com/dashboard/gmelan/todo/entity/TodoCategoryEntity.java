//package com.dashboard.gmelan.todo.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.sql.Timestamp;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "todo-category")
//public class TodoCategoryEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name="name", unique = true, nullable = false, columnDefinition = "varchar(20)")
//    private String name;
//
//    @Column(name="created_at", columnDefinition = "datetime default current_timestamp()")
//    private Timestamp created_at;
//}
