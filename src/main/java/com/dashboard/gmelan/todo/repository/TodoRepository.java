package com.dashboard.gmelan.todo.repository;

import com.dashboard.gmelan.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    // 사용자 ID를 기반으로 할 일 목록을 가져오는 메서드 정의
    List<TodoEntity> getTodoListByUserId(Long userId);

}
