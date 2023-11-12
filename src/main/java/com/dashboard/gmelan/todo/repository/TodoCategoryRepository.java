package com.dashboard.gmelan.todo.repository;

import com.dashboard.gmelan.todo.entity.TodoCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoCategoryRepository extends JpaRepository<TodoCategory, Long> {
    TodoCategory findByName(String name);
}
