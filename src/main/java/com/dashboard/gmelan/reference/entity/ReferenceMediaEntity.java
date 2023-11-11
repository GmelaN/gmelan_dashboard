//package com.dashboard.gmelan.reference.entity;
//
//import com.dashboard.gmelan.mediaEntity.MediaEntity;
//import com.dashboard.gmelan.notice.entity.NoticeEntity;
//import jakarta.persistence.*;
//
//@Entity(name = "reference-media")
//public class ReferenceMediaEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "reference_id")
//    private ReferenceEntity reference;
//
//    @ManyToOne
//    @JoinColumn(name="media_id")
//    private MediaEntity media;
//}
