package com.dashboard.gmelan.todo.controller;

import com.dashboard.gmelan.todo.entity.Todo;
import com.dashboard.gmelan.todo.entity.TodoCategory;
import com.dashboard.gmelan.todo.repository.TodoRepository;
import com.dashboard.gmelan.user.Entity.UserEntity;
import com.dashboard.gmelan.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dashboard.gmelan.todo.service.TodoService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Todo_ list CRUD controller.
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
    private UserService userService;


    @GetMapping("/todos/{userId}")
    public String showTodoPage(@PathVariable Long userId, Model model) {
        UserEntity user = userService.findByUserId(userId);
        List<Todo> todoEntityList = todoService.getAllTodos(user);

        model.addAttribute("todoList", todoEntityList);
        model.addAttribute("userId", userId);

        return "todo";
    }

    @PostMapping("/todos/{userId}/createTodo")
    public String createTodo(
            @PathVariable Long userId,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content") String content,
            @RequestParam(name = "url") String url,
            @RequestParam(name = "startDate") String startDate,
            @RequestParam(name = "endDate") String endDate,
            @RequestParam(name = "category") String category

    ) {
        UserEntity user = userService.findByUserId(userId);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd");

        // _Todo 엔티티에 파라미터 값들 대입
        Todo newTodo = new Todo();
        newTodo.setUser(user);
        newTodo.setTitle(title);
        if(!content.isEmpty()) newTodo.setContent(content);
        if(!url.isEmpty()) newTodo.setUrl(url);
        newTodo.setTodoCategoryEntity(todoService.getOrCreateCategory(category));

        try {
            if(!startDate.isEmpty()) newTodo.setStartDate(new Timestamp(dateFormatter.parse(startDate).getTime()));
            if(!endDate.isEmpty()) newTodo.setEndDate(new Timestamp(dateFormatter.parse(endDate).getTime()));
        } catch(ParseException e) {
            System.err.println("createTodo: date parsing error.");
            return "";
        }
        
        // 만들어진 todo 저장
        try {
            todoService.createTodo(newTodo);
        } catch(Exception e) {
            System.err.println("There was an error while creating new to-do task.");
            return "";
        }

        return "redirect:/todos/{userId}";
    }

    @GetMapping("/todos/json/{userId}")
    public ResponseEntity<List<Todo>> getTodosById(@PathVariable Long userId) {
        UserEntity user = userService.findByUserId(userId);

        // TodoService를 사용하여 특정 ID의 할 일 항목을 가져옵니다.
        List<Todo> todoList = todoService.getAllTodos(user);

        if (todoList != null) {
            return new ResponseEntity<>(todoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // todo: 권한 확인 필요
    @PutMapping("/todos/update/{taskId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long taskId, Todo updatedTodoEntity) {
        // TODO: 사용자 권한 검사
        Todo result = todoService.updateTodo(taskId, updatedTodoEntity);

        // 업데이트 실패 시
        if(result == null) {
            System.err.println("update to-do failed. updateTodo returned null.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // todo: 권한 확인 필요
    @DeleteMapping("/todos/delete/{taskId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long taskId) {
        // TODO: userId로 현재 로그인 한 사용자가 맞는지 검사
        // TodoService를 사용하여 특정 ID의 할 일 항목을 삭제합니다.
        boolean isDeleted = todoService.deleteTodo(taskId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


