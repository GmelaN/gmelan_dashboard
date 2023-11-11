//package com.dashboard.gmelan.todo.entity;
//
//import com.dashboard.gmelan.mediaEntity.MediaEntity;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.List;
//
//@Entity(name="todo-media")
//@Getter
//@Setter
//public class TodoMediaEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    // mapped columns: todo_id, id(media)
//
//    @ManyToOne
//    @JoinColumn(name="todo_id")
//    private TodoEntity todo;
//
//    @ManyToOne
//    @JoinColumn(name="media_id")
//    private MediaEntity media;
//}
