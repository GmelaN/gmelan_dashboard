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
@Table(name = "jwt_token", schema = "dashboard")
public class JwtToken {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BIGINT NOT NULL")
    private UserEntity userId;

    @Basic
    @Column(name = "token", columnDefinition = "VARCHAR(255) NOT NULL UNIQUE")
    private String token;

    @Basic
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Basic
    @Column(name = "expire_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp expireAt;
}
