//package com.dashboard.gmelan.news.entity;
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
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "news-media", schema = "dashboard")
//public class NewsMediaEntity {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "id")
//    private long id;
//    @Basic
//    @Column(name = "media_id")
//    private long mediaId;
//    @Basic
//    @Column(name = "news_id")
//    private long newsId;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        NewsMediaEntity that = (NewsMediaEntity) o;
//
//        if (id != that.id) return false;
//        if (mediaId != that.mediaId) return false;
//        if (newsId != that.newsId) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = (int) (id ^ (id >>> 32));
//        result = 31 * result + (int) (mediaId ^ (mediaId >>> 32));
//        result = 31 * result + (int) (newsId ^ (newsId >>> 32));
//        return result;
//    }
//}
