package com.dashboard.gmelan.user.Entity;

//import com.dashboard.gmelan.reference.entity.ReferenceEntity;
//import com.dashboard.gmelan.todo.entity.TodoEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, columnDefinition = "varchar(15)")
    private String username;

    @Column(name = "password", nullable = false, columnDefinition = "varchar(128)")
    private String password;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "varchar(30)")
    private String email;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    private Timestamp created_at;

    @Column(name = "is_available", nullable = false, columnDefinition = "default true")
    private boolean is_available;

    @Column(name = "type", nullable = false, columnDefinition = "varchar(10) default 'IN_APP'")
    private String type;

    // mapped columns: user, permission, JWT_token, reference
//    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
//    private List<TodoEntity> todos;
//
//    @OneToMany(mappedBy = "jwt_token", cascade = CascadeType.ALL)
//    private List<JWTTokenEntity> JWTTokens;
//
//    @OneToOne(mappedBy = "permission", cascade = CascadeType.ALL)
//    private UserPermissionEntity permission;
//
//    @OneToMany(mappedBy = "reference", cascade = CascadeType.ALL)
//    private List<ReferenceEntity> reference;

}
