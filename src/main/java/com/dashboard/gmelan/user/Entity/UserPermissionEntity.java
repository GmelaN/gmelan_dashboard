package com.dashboard.gmelan.user.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permission", schema = "dashboard")
public class UserPermissionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY")
    private long id;

    @Basic
    @Column(name = "permission", length = 10, nullable = false, unique = true, columnDefinition = "VARCHAR(10) NOT NULL UNIQUE DEFAULT 'USER'")
    private String permission;

    @Basic
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Basic
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Timestamp updatedAt;
}
