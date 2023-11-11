//package com.dashboard.gmelan.todo.entity;
//
//import com.dashboard.gmelan.mediaEntity.MediaEntity;
//import com.dashboard.gmelan.user.Entity.UserEntity;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//
//@Entity
//@Getter
//@Setter
//@Table(name = "todo")
//public class TodoEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "title", nullable = false, columnDefinition = "varchar(30) default '제목'")
//    private String title;
//
//    @Column(name = "content", columnDefinition = "text default '내용 없음'")
//    private String content;
//
//    @Column(name = "start_date")
//    private Timestamp startDate;
//
//    @Column(name = "end_date")
//    private Timestamp endDate;
//
//    @Column(name = "url")
//    private String url;
//
//
//    // mapped columns: todo_category_id, user_id, todo_media
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity user;
//
//    @ManyToOne
//    @JoinColumn(name = "todo_category_id")
//    private TodoCategoryEntity todoCategory;
//
//    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
//    private List<MediaEntity> todoMedia;
//}