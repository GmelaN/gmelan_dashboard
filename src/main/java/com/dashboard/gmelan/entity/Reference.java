package com.dashboard.gmelan.entity;

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
    @Column(name = "id", columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BIGINT NOT NULL")
    private UserEntity user;

    @Basic
    @Column(name = "title", columnDefinition = "VARCHAR(30) NOT NULL DEFAULT '제목 없음'")
    private String title;

    @Basic
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Basic
    @Column(name = "url", columnDefinition = "VARCHAR(50) NULL DEFAULT 'URL 없음'")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reference_category_id", columnDefinition = "BIGINT NOT NULL")
    private ReferenceCategory referenceCategory;

    @OneToMany(mappedBy = "reference", cascade = CascadeType.ALL)
    private List<ReferenceMedia> referenceMedia;


    @Basic
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Basic
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Timestamp updatedAt;

}
