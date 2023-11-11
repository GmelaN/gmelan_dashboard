//package com.dashboard.gmelan.news.entity;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//
///**
// *
// */
//@Getter
//@Setter
//@Entity(name="news")
//public class NewsEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "title", nullable = false, columnDefinition = "varchar(20) not null default '제목'")
//    private String title;
//
//    @Column(name = "published_at", nullable = false, columnDefinition = "datetime not null default CURRENT_TIMESTAMP")
//    private Timestamp published_at;
//
//    @Column(name = "content", columnDefinition = "text default '내용 없음'")
//    private String content;
//
//    @Column(name = "url", columnDefinition = "varchar(50)")
//    private String url;
//
//    @Column(name = "fetched_at", nullable = false, columnDefinition = "datetime not null default CURRENT_TIMESTAMP")
//    private Timestamp fetched_at;
//
//    @Column(name = "category", nullable = false, columnDefinition = "varchar(20) not null default '분류 없음'")
//    private String category;
//
//    @Column(name = "company", columnDefinition = "varchar(20)")
//    private String company;
//
//    @Column(name = "reporter", columnDefinition = "varchar(20)")
//    private String reporter;
//
//    @OneToMany(mappedBy = "news-media")
//    private List<NewsMediaEntity> medias;
//}
//
