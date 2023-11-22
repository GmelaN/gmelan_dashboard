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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jwt_token", schema = "dashboard")
public class JwtTokenEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BIGINT NOT NULL")
    private UserEntity userId;

    @Basic
    @Column(name = "token", length = 255, nullable = false, unique = true)
    private String token;

    @Basic
    @Column(name = "created_at", nullable = false, columnDefinition = "DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Basic
    @Column(name = "expire_at", nullable = false)
    private Timestamp expireAt;
}
