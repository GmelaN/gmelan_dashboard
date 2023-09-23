package com.dashboard.gmelan.service;

import com.dashboard.gmelan.dataStructure.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // 사용자 ID를 기반으로 할 일 목록을 가져오는 메서드 정의
    List<Todo> findByuser_id(Long userId);
}

