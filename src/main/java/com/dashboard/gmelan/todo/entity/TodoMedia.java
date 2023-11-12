package com.dashboard.gmelan.todo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todo-media", schema = "dashboard")
public class TodoMedia {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "media_id")
    private long mediaId;
    @Basic
    @Column(name = "todo_id")
    private long todoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TodoMedia that = (TodoMedia) o;

        if (id != that.id) return false;
        if (mediaId != that.mediaId) return false;
        if (todoId != that.todoId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (mediaId ^ (mediaId >>> 32));
        result = 31 * result + (int) (todoId ^ (todoId >>> 32));
        return result;
    }
}
