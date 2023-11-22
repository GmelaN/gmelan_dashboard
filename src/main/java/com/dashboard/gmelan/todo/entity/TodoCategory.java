package com.dashboard.gmelan.todo.entity;

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
@NoArgsConstructor
@Table(name = "todo-category", schema = "dashboard")
public class TodoCategory {
    public TodoCategory(String name) {
        this.name = name;
    }
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true, columnDefinition = "BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY")
    private long id;

    @Basic
    @Column(name = "name", nullable = false, length = 20, unique = true, columnDefinition = "VARCHAR(20) NOT NULL UNIQUE DEFAULT '기본 분류'")
    private String name;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
    private List<Todo> todos;

    @Basic
    @Column(name = "created_at", nullable = false, columnDefinition = "NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Basic
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Timestamp updatedAt;
}
