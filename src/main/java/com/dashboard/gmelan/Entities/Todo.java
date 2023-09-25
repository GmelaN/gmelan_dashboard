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
    @Getter
    private Long taskId;

    @Column(name = "user_id")
    @Getter
    private Long userId;

    @Column(name = "task_data")
    @Getter
    @Setter
    private String taskData;

    @Column(name = "created_at")
    @Getter
    @Setter
    private Timestamp createdAt;

    @Column(name = "status")
    @Getter
    @Setter
    private String status;
}
