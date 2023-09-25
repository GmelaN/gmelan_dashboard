package com.dashboard.gmelan.controller;

import com.dashboard.gmelan.Entities.Todo;
import com.dashboard.gmelan.service.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dashboard.gmelan.Entities.Todo;
import com.dashboard.gmelan.service.TodoService;

import java.sql.Timestamp;
import java.util.List;

/**
 * Todo list CRUD controller.
 * @author gmelan
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 * @see <a href="velog.com/gmelan">author's blog</a>
 */
@Service
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoRepository todoRepository;

    @PostMapping("/todos/{userId}/createTodo")
    public ResponseEntity<Todo> createTodo(@PathVariable Long userId, @RequestBody Todo todo) {
        // TodoService를 사용하여 새로운 할 일 항목을 생성하고 저장합니다.
        Todo createdTodo = todoService.createTodo(userId, todo);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @GetMapping("/todos/{userId}")
    public ResponseEntity<List<Todo>> getTodosById(@PathVariable Long userId) {
        // TodoService를 사용하여 특정 ID의 할 일 항목을 가져옵니다.
        List<Todo> todoList = todoService.getTodoListByUserId(userId);

        if (todoList != null) {
            return new ResponseEntity<>(todoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/todos/{userId}/{taskId}")
    public Todo updateTodo(@PathVariable Long taskId, Todo updatedTodo) {
        // 1. 엔터티를 조회하여 업데이트 대상 엔터티를 가져옵니다.
        Todo existingTodo = todoRepository.findById(taskId).orElse(null);

        // 업데이트 대상 엔터티가 존재하지 않으면 null을 반환하거나 예외 처리할 수 있습니다.
        if (existingTodo == null) {
            return null; // 또는 예외 처리 로직 추가
        }

        // 2. 엔터티의 필드를 변경합니다.
        existingTodo.setTaskData(updatedTodo.getTaskData()); // 예시로 taskData 필드를 업데이트
        existingTodo.setStatus(updatedTodo.getStatus());

        // 시각은 현재 시각으로
        existingTodo.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        // 3. 변경된 엔터티를 저장하면 업데이트가 완료됩니다.
        return todoRepository.save(existingTodo);
    }

    @DeleteMapping("/todos/{userId}/{taskId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long userId, @PathVariable Long taskId) {
        // todo: userId로 현재 로그인 한 사용자가 맞는지 검사

        // TodoService를 사용하여 특정 ID의 할 일 항목을 삭제합니다.
        boolean deleted = todoService.deleteTodo(taskId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


