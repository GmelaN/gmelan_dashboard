package com.dashboard.gmelan.notice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notice", schema = "dashboard")
public class NoticeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "published_at")
    private Timestamp publishedAt;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "url")
    private String url;
    @Basic
    @Column(name = "agency")
    private String agency;
    @Basic
    @Column(name = "fetched_at")
    private Timestamp fetchedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoticeEntity that = (NoticeEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (publishedAt != null ? !publishedAt.equals(that.publishedAt) : that.publishedAt != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (agency != null ? !agency.equals(that.agency) : that.agency != null) return false;
        if (fetchedAt != null ? !fetchedAt.equals(that.fetchedAt) : that.fetchedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (publishedAt != null ? publishedAt.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (agency != null ? agency.hashCode() : 0);
        result = 31 * result + (fetchedAt != null ? fetchedAt.hashCode() : 0);
        return result;
    }
}
