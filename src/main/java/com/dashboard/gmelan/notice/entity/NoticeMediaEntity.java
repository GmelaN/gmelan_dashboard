//package com.dashboard.gmelan.notice.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "notice-media", schema = "dashboard", catalog = "")
//public class NoticeMediaEntity {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "id")
//    private long id;
//    @Basic
//    @Column(name = "media_id")
//    private long mediaId;
//    @Basic
//    @Column(name = "notice_id")
//    private long noticeId;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        NoticeMediaEntity that = (NoticeMediaEntity) o;
//
//        if (id != that.id) return false;
//        if (mediaId != that.mediaId) return false;
//        if (noticeId != that.noticeId) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = (int) (id ^ (id >>> 32));
//        result = 31 * result + (int) (mediaId ^ (mediaId >>> 32));
//        result = 31 * result + (int) (noticeId ^ (noticeId >>> 32));
//        return result;
//    }
//}
