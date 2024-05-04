package com.dashboard.gmelan.entity;


import com.dashboard.gmelan.entity.enums.UserPermission;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@Table(name = "user", schema = "dashboard")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT")
    private Long id;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "VARCHAR(10) NOT NULL DEFAULT 'USER'")
    private UserPermission type = UserPermission.USER;

    @Basic
    @Column(name = "username", columnDefinition = "VARCHAR(15) NOT NULL UNIQUE")
    private String username;

    @Basic
    @Column(name = "password", columnDefinition = "VARCHAR(128) NOT NULL")
    private String password;

    @Basic
    @Column(name = "email", columnDefinition = "VARCHAR(30) NOT NULL UNIQUE")
    private String email;

    @Basic
    @Builder.Default
    @Column(name = "is_available", columnDefinition = "BOOLEAN NOT NULL DEFAULT true")
    private Boolean isAvailable = true;

    @Basic
    @Builder.Default
    @Column(name = "provider", columnDefinition = "VARCHAR(10) NOT NULL DEFAULT 'IN_APP'")
    private String provider = "IN_APP";

    @Basic
    @Builder.Default
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @Basic
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Timestamp updatedAt;

}
