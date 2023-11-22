package com.dashboard.gmelan.reference.entity;

import com.dashboard.gmelan.media.entity.Media;
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
@Table(name = "reference-media", schema = "dashboard")
public class ReferenceMedia {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true, columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reference_id", nullable = false, updatable = false, unique = true, columnDefinition = "BIGINT NOT NULL")
    private Reference reference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id", nullable = false, updatable = false, unique = false, columnDefinition = "BIGINT NOT NULL")
    private Media media;

    @Basic
    @Column(name = "created_at", nullable = false, columnDefinition = "NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Basic
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Timestamp updatedAt;
}
