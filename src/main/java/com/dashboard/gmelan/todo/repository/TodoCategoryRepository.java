package com.dashboard.gmelan.todo.repository;

import com.dashboard.gmelan.todo.entity.TodoCategoryEntity;
import com.dashboard.gmelan.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoCategoryRepository extends JpaRepository<TodoCategoryEntity, Long> {
    TodoCategoryEntity findByName(String name);
}
