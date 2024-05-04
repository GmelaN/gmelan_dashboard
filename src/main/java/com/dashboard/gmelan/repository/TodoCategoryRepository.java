package com.dashboard.gmelan.repository;

import com.dashboard.gmelan.entity.TodoCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoCategoryRepository extends JpaRepository<TodoCategory, Long> {
    TodoCategory findByName(String name);
}
