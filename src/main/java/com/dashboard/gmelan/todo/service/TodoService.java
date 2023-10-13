package com.dashboard.gmelan.todo.service;

import com.dashboard.gmelan.todo.entity.TodoEntity;
import com.dashboard.gmelan.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // 사용자 ID를 기반으로 할 일 목록을 가져오는 메서드
    public List<TodoEntity> getTodoListByUserId(Long userId) {
        return todoRepository.getTodoListByUserId(userId);
    }

    public TodoEntity createTodo(Long userId, TodoEntity todoEntity) {
        return todoRepository.save(todoEntity);
    }

    public boolean deleteTodo(Long task_id) {
        try {
            todoRepository.deleteById(task_id);
        } catch(Exception e) {
            // 삭제 실패시 false
            return false;
        }
        // 삭제 성공 시 true
        return true;
    }

    public TodoEntity updateTodo(Long taskId, TodoEntity updatedTodoEntity) {
        // 1. 엔터티를 조회하여 업데이트 대상 엔터티를 가져옵니다.
        TodoEntity existingTodoEntity = todoRepository.findById(taskId).orElse(null);

        // 업데이트 대상 엔터티가 존재하지 않으면 null을 반환하거나 예외 처리할 수 있습니다.
        if (existingTodoEntity == null) {
            return null; // 또는 예외 처리 로직 추가
        }

        // 2. 엔터티의 필드를 변경합니다.
        existingTodoEntity.setTaskData(updatedTodoEntity.getTaskData()); // 예시로 taskData 필드를 업데이트
        existingTodoEntity.setStatus(updatedTodoEntity.getStatus());

        // 시각은 현재 시각으로
        existingTodoEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        // 3. 변경된 엔터티를 저장하면 업데이트가 완료됩니다.
        return todoRepository.save(existingTodoEntity);
    }
}