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
    @Column(name = "id", columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT")
    private long id;


    @Column(name = "permission_id", nullable = false)
    private long permissionId;

    @Basic
    @Column(name = "username", columnDefinition = "VARCHAR(15) NOT NULL UNIQUE")
    private String username;

    @Basic
    @Column(name = "password", columnDefinition = "VARCHAR(128) NOT NULL ")
    private String password;

    @Basic
    @Column(name = "email", columnDefinition = "VARCHAR(30) NOT NULL UNIQUE")
    private String email;

    @Basic
    @Column(name = "is_available", columnDefinition = "BOOLEAN NOT NULL DEFAULT true")
    private boolean isAvailable;

    @Basic
    @Column(name = "type", columnDefinition = "VARCHAR(10) NOT NULL DEFAULT 'IN_APP'")
    private String type;

    @Basic
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Basic
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Timestamp updatedAt;

}
