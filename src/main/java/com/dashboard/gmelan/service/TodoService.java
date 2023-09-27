package com.dashboard.gmelan.service;

import com.dashboard.gmelan.Entities.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // 사용자 ID를 기반으로 할 일 목록을 가져오는 메서드
    public List<Todo> getTodoListByUserId(Long userId) {
        return todoRepository.getTodoListByUserId(userId);
    }

    public Todo createTodo(Long userId, Todo todo) {
        return todoRepository.save(todo);
    }

    public boolean deleteTodo(Long task_id) {
        try {
            todoRepository.deleteById(task_id);
        } catch(Exception e) {
            return false;
        }
        return true;
    }
}