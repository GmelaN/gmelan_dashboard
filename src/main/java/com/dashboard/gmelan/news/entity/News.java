package com.dashboard.gmelan.news.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "news", schema = "dashboard")
public class News {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "id")
    private long id;
//    @Basic
//    @Column(name = "title")
    private String title;
//    @Basic
//    @Column(name = "published_at")
    private Timestamp publishedAt;
//    @Basic
//    @Column(name = "content")
    private String content;
//    @Basic
//    @Column(name = "url")
    private String url;
//    @Basic
//    @Column(name = "fetched_at")
    private Timestamp fetchedAt;
//    @Basic
//    @Column(name = "category")
    private String category;
//    @Basic
//    @Column(name = "company")
    private String company;
//    @Basic
//    @Column(name = "reporter")
    private String reporter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News that = (News) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (publishedAt != null ? !publishedAt.equals(that.publishedAt) : that.publishedAt != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (fetchedAt != null ? !fetchedAt.equals(that.fetchedAt) : that.fetchedAt != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (reporter != null ? !reporter.equals(that.reporter) : that.reporter != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (publishedAt != null ? publishedAt.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (fetchedAt != null ? fetchedAt.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (reporter != null ? reporter.hashCode() : 0);
        return result;
    }
}
