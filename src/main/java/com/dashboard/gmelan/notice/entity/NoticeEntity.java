//package com.dashboard.gmelan.notice.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Getter
//@Setter
//@Entity(name="notice")
//public class NoticeEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "title", nullable = false, columnDefinition = "varchar(30) default '제목'")
//    private String title;
//
//    @Column(name = "content", columnDefinition = "text default '내용 없음'")
//    private String content;
//
//    @Column(name = "published_at", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
//    private Timestamp published_at;
//
//    @Column(name = "fetched_at", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
//    private Timestamp fetched_at;
//
//    @Column(name = "agency", columnDefinition = "varchar(20)")
//    private String agency;
//
//    @Column(name = "url")
//    private String url;
//}
