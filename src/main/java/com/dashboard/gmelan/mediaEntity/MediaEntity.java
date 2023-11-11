package com.dashboard.gmelan.mediaEntity;

import com.dashboard.gmelan.news.entity.NewsMediaEntity;
import com.dashboard.gmelan.notice.entity.NoticeMediaEntity;
import com.dashboard.gmelan.reference.entity.ReferenceMediaEntity;
import com.dashboard.gmelan.todo.entity.TodoMediaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

/**
 * Media Entity.
 */
@Getter
@Setter
@Entity(name="media")
public class MediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", columnDefinition="varchar(50)")
    private String url;

    @Column(name="uploaded_at", columnDefinition = "datetime not null default CURRENT_TIMESTAMP")
    private Timestamp uploaded_at;

    @Column(name="type", columnDefinition = "varchar(10)")
    private String type;

    // mapped columns: todo_-media, notice-media, reference-media, news-media
    @OneToMany(mappedBy="todo-media", cascade=CascadeType.ALL)
    private List<TodoMediaEntity> todoMedia;

    @OneToMany(mappedBy="notice-media", cascade=CascadeType.ALL)
    private List<NoticeMediaEntity> noticeMedia;

    @OneToMany(mappedBy="reference-media", cascade=CascadeType.ALL)
    private List<ReferenceMediaEntity> referenceMedia;

    @OneToMany(mappedBy="news-media", cascade=CascadeType.ALL)
    private List<NewsMediaEntity> newsMedia;
}
