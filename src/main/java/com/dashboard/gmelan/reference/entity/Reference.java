package com.dashboard.gmelan.reference.entity;

import com.dashboard.gmelan.todo.entity.TodoCategory;
import com.dashboard.gmelan.todo.entity.TodoMedia;
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
@Table(name = "reference", schema = "dashboard")
public class Reference {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true, columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false, unique = true, columnDefinition = "BIGINT NOT NULL")
    private UserEntity user;

    @Basic
    @Column(name = "title", length = 30, nullable = false, columnDefinition = "VARCHAR(30) NOT NULL DEFAULT '제목 없음'")
    private String title;

    @Basic
    @Column(name = "content", columnDefinition = "TEXT NULL DEFAULT '내용 없음'")
    private String content;

    @Basic
    @Column(name = "url", length = 50, columnDefinition = "VARCHAR(50) NULL DEFAULT 'URL 없음'")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reference_category_id", nullable = false, updatable = false, unique = true, columnDefinition = "BIGINT NOT NULL")
    private ReferenceCategory referenceCategory;

    @OneToMany(mappedBy = "ReferenceMedia", cascade = CascadeType.ALL)
    private List<ReferenceMedia> referenceMedia;

    @Basic
    @Column(name = "created_at", nullable = false, columnDefinition = "NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Basic
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Timestamp updatedAt;

}
