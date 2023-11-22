package com.dashboard.gmelan.user.Entity;

import com.dashboard.gmelan.todo.entity.Todo;
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
@Table(name = "user", schema = "dashboard")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true, columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY")
    private long id;


    @Column(name = "permission_id", nullable = false)
    private long permissionId;

    @Basic
    @Column(name = "username", length = 15, columnDefinition = "VARCHAR(15) NOT NULL UNIQUE")
    private String username;

    @Basic
    @Column(name = "password", length = 128, columnDefinition = "VARCHAR(128) NOT NULL ")
    private String password;

    @Basic
    @Column(name = "email", length = 30, columnDefinition = "NOT NULL UNIQUE")
    private String email;

    @Basic
    @Column(name = "is_available", columnDefinition = "BOOLEAN NOT NULL DEFAULT true")
    private boolean isAvailable;

    @Basic
    @Column(name = "type", length = 10, columnDefinition = "VARCHAR(10) NOT NULL DEFAULT 'IN_APP'")
    private String type;

    @Basic
    @Column(name = "created_at", nullable = false, columnDefinition = "DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Basic
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Timestamp updatedAt;
}
