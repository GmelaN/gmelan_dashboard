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
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "user_id")
    private long userId;
    @Basic
    @Column(name = "token")
    private String token;
    @Basic
    @Column(name = "expire_at")
    private Timestamp expireAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JwtTokenEntity that = (JwtTokenEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (expireAt != null ? !expireAt.equals(that.expireAt) : that.expireAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (expireAt != null ? expireAt.hashCode() : 0);
        return result;
    }
}
