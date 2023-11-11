package com.dashboard.gmelan.reference.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reference-media", schema = "dashboard")
public class ReferenceMediaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "reference_id")
    private long referenceId;
    @Basic
    @Column(name = "media_id")
    private long mediaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReferenceMediaEntity that = (ReferenceMediaEntity) o;

        if (id != that.id) return false;
        if (referenceId != that.referenceId) return false;
        if (mediaId != that.mediaId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (referenceId ^ (referenceId >>> 32));
        result = 31 * result + (int) (mediaId ^ (mediaId >>> 32));
        return result;
    }
}
