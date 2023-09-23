package com.dashboard.gmelan.dataStructure;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;


@Entity
@Table(name = "todo_list")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "task_data", columnDefinition = "JSON")
    private String task_data;

    @Column(name = "created_at")
    private Timestamp created_at;
}
