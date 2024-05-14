package com.dashboard.gmelan.repository;

import com.dashboard.gmelan.entity.TodoCategory;
import com.dashboard.gmelan.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // 1. 사용자별 전체 _todo 리스트 조회 기능
    List<Todo> findByUserId(long userId);

    // 2. 사용자가 새로운 _todo 생성/삭제/수정 기능
    // (생성은 JpaRepository의 save()를 사용하므로 별도의 메서드는 필요 없음)

    // 삭제
    void deleteByIdAndUserId(long id, long userId);

    // 3. 사용자별, end_date가 없거나 end_date가 현재보다 작은 todo만 조회하는 기능
    List<Todo> findByUserIdAndStartDateIsNullOrStartDateBefore(long userId, Timestamp now);
    List<Todo> findByUserIdAndEndDateIsBefore(long userId, Timestamp now);

    // 4. 사용자별 특정 카테고리에 있는 _todo 리스트 조회 기능
    List<Todo> findByUserIdAndTodoCategory(long userId, TodoCategory categoryEntity);

    List<Todo> findByUserIdAndStartDateIsNullAndEndDateIsNull(long userId);

    // 내부 구현을 위한 메서드: 카테고리별 _todo의 수 조사

    Long countByTodoCategory(TodoCategory category);

}
