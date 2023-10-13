package com.dashboard.gmelan.todo.controller;

import com.dashboard.gmelan.todo.entity.TodoEntity;
import com.dashboard.gmelan.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dashboard.gmelan.todo.service.TodoService;

import java.util.List;

/**
 * Todo list CRUD controller.
 * @author gmelan
 * @version 0.0.1-SNAPSHOT
 * @since 0.0.1-SNAPSHOT
 * @see <a href="velog.com/gmelan">author's blog</a>
 */
@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoRepository todoRepository;


    @GetMapping("/todos/{userId}")
    public String showTodoPage(@PathVariable Long userId, Model model) {
        List<TodoEntity> todoEntityList = todoService.getTodoListByUserId(userId);

        model.addAttribute("todoList", todoEntityList);
        model.addAttribute("userId", userId);

        return "todo";
    }

    @PostMapping("/todos/{userId}/createTodo")
    public ResponseEntity<TodoEntity> createTodo(@PathVariable Long userId, @RequestParam TodoEntity todoEntity) {
        // TodoService를 사용하여 새로운 할 일 항목을 생성하고 저장합니다.
        TodoEntity createdTodoEntity = null;

        try {
            createdTodoEntity = todoService.createTodo(userId, todoEntity);
        } catch(Exception e) {
            System.err.println("There was an error while creating new to-do task.");
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(createdTodoEntity, HttpStatus.CREATED);
    }

    @GetMapping("/todos/json/{userId}")
    public ResponseEntity<List<TodoEntity>> getTodosById(@PathVariable Long userId) {
        // TodoService를 사용하여 특정 ID의 할 일 항목을 가져옵니다.
        List<TodoEntity> todoEntityList = todoService.getTodoListByUserId(userId);

        if (todoEntityList != null) {
            return new ResponseEntity<>(todoEntityList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 권한 확인 필요
    @PutMapping("/todos/update/{taskId}")
    public ResponseEntity<TodoEntity> updateTodo(@PathVariable Long taskId, TodoEntity updatedTodoEntity) {
        TodoEntity result = todoService.updateTodo(taskId, updatedTodoEntity);
        
        // 업데이트 실패 시
        if(result == null) {
            System.err.println("update to-do failed. updateTodo returned null.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 권한 확인 필요
    @DeleteMapping("/todos/delete/{taskId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long taskId) {
        // todo: userId로 현재 로그인 한 사용자가 맞는지 검사
        // TodoService를 사용하여 특정 ID의 할 일 항목을 삭제합니다.
        boolean isDeleted = todoService.deleteTodo(taskId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


