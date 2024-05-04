package com.dashboard.gmelan.entity;

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
    @Column(name = "id", columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reference_id", columnDefinition = "BIGINT NOT NULL UNIQUE")
    private Reference reference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id", columnDefinition = "BIGINT NOT NULL UNIQUE")
    private Media media;

    @Basic
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Basic
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Timestamp updatedAt;

}
