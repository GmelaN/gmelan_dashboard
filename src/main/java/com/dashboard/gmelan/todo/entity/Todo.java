package com.dashboard.gmelan.todo.entity;

import com.dashboard.gmelan.user.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todo", schema = "dashboard")
public class Todo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT")
    private long id;

    @Basic
    @Column(name = "title", columnDefinition = "VARCHAR(30) NOT NULL DEFAULT '제목'")
    private String title;

    @Basic
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Basic
    @Column(name = "url", columnDefinition = "VARCHAR(50) NULL DEFAULT 'URL 없음'")
    private String url;

    @Basic
    @Column(name = "start_date", columnDefinition = "TIMESTAMP NULL")
    private Timestamp startDate;

    @Basic
    @Column(name = "end_date", columnDefinition = "TIMESTAMP NULL")
    private Timestamp endDate;

    @Transient
    private String categoryName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", columnDefinition = "BIGINT NOT NULL")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "todo_category_id", columnDefinition = "BIGINT NOT NULL")
    private TodoCategory todoCategory;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TodoMedia> todoMedia;

    @Basic
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Basic
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Timestamp updatedAt;
}
