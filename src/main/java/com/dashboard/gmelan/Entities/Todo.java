package com.dashboard.gmelan.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Table(name = "todo_list")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;

    @Column(name = "user_id")
    @Getter
    private Long userId;

    @Column(name = "task_data")
    @Getter
    private String task_data;

    @Column(name = "created_at")
    @Getter
    private Timestamp created_at;

    @Column(name = "status")
    @Getter
    @Setter
    private String status;
}
