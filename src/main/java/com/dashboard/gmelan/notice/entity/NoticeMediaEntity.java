//package com.dashboard.gmelan.notice.entity;
//
//import com.dashboard.gmelan.mediaEntity.MediaEntity;
//import jakarta.persistence.*;
//
//@Entity(name="notice-media")
//public class NoticeMediaEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "notice_id")
//    private NoticeEntity notice;
//
//    @ManyToOne
//    @JoinColumn(name="media_id")
//    private MediaEntity media;
//}
