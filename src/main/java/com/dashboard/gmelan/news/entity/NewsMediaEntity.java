//package com.dashboard.gmelan.news.entity;
//
//import com.dashboard.gmelan.mediaEntity.MediaEntity;
//import jakarta.persistence.*;
//
//@Entity(name="news-media")
//public class NewsMediaEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "news_id")
//    private NewsEntity news;
//
//    @ManyToOne
//    @JoinColumn(name="media_id")
//    private MediaEntity media;
//
//}
