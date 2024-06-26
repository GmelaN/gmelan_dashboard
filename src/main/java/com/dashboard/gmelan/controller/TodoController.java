package com.dashboard.gmelan.controller;

import com.dashboard.gmelan.entity.Todo;
import com.dashboard.gmelan.entity.TodoCategory;
import com.dashboard.gmelan.entity.UserEntity;
import com.dashboard.gmelan.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dashboard.gmelan.service.TodoService;

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
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;
    private final UserService userService;

    public TodoController(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    // _todo 페이지
    @GetMapping("/list")
    public String showTodoPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if(userDetails == null) {
            return "redirect:/user/login";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserEntity user = userService.findByUsername(username);

        if(user == null) {
            return "error";
        }

        List<Todo> todoEntityList = todoService.getAllTodos(user);

        model.addAttribute("todoList", todoEntityList);
        model.addAttribute("userId", user.getId());

        return "todo";
    }

    // 새 할 일 만들기
    @PostMapping("/create")
    public String createTodo(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content") String content,
            @RequestParam(name = "url") String url,
            @RequestParam(name = "startDate") String startDate,
            @RequestParam(name = "endDate") String endDate,
            @RequestParam(name = "category") String category

    ) {
        if(userDetails == null) {
            return "redirect:/user/login";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserEntity user = userService.findByUsername(username);

        if(user == null) {
            return "error";
        }

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        // _Todo 엔티티에 파라미터 값들 대입
        Todo newTodo = new Todo();
        newTodo.setUser(user);
        newTodo.setTitle(title);
        if(!content.isEmpty()) newTodo.setContent(content);
        if(!url.isEmpty()) newTodo.setUrl(url);

        try {
            if(!startDate.isEmpty()) newTodo.setStartDate(new Timestamp(dateFormatter.parse(startDate).getTime()));
            if(!endDate.isEmpty()) newTodo.setEndDate(new Timestamp(dateFormatter.parse(endDate).getTime()));
        } catch(ParseException e) {
            System.err.println("createTodo: date parsing error.");
            return "error";
        } catch (IllegalArgumentException e) {
            System.err.println("createTodo: illegal argument passed.");
            return "error";
        }
        
        // 카테고리가 지정되지 않음 -> 기본 카테고리 지정하기
        if(category.isEmpty()) {
            // 일자 정보가 있다면 카테고리 지정 가능
            Timestamp startTimestamp = newTodo.getStartDate();
            Timestamp endTimestamp = newTodo.getEndDate();
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            
            category = "분류 없음"; // 기본값

            if(startTimestamp != null) { // 시작 날짜가 기입되어 있는 경우
                if(startTimestamp.before(currentTimestamp)) category = "진행 중"; // 시작이 현재보다 앞
                else category = "진행 전"; // 시작이 현재보다 뒤
            }
            if(endTimestamp != null && endTimestamp.before(currentTimestamp)) category = "진행 완료"; // 종료가 현재보다 앞
            if(endTimestamp != null && endTimestamp.after(currentTimestamp)) category = "진행 중"; // 종료가 현재보다 뒤
        }
        newTodo.setTodoCategory(todoService.getOrCreateCategory(category));
        
        // 만들어진 _todo 저장
        try {
            todoService.createTodo(newTodo);
        } catch(Exception e) {
            System.err.println("There was an error while creating new to-do task.");
            return "error";
        }
        return "redirect:/todos/list";
    }

    // 모든 할 일 조회
    @GetMapping("/json/all")
    @PreAuthorize("hasRole('ROLE_USER') and userService.findByUserId(#userId).username == principal.username")
    public ResponseEntity<List<Todo>> getAllTodosById(@PathVariable Long userId) {
        UserEntity user = userService.findByUserId(userId);

        // TodoService를 사용하여 특정 ID의 할 일 항목을 가져옵니다.
        List<Todo> todoList = todoService.getAllTodos(user);

        if (todoList != null) {
            return new ResponseEntity<>(todoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/json/{taskId}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long taskId) {
        // TodoService를 사용하여 특정 ID의 할 일 항목을 가져옵니다.
        Todo todo = todoService.getTodoById(taskId);

        if (todo != null) {
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 진행 중인 할 일 조회
    @GetMapping("/json/active")
    @PreAuthorize("hasRole('ROLE_USER') and userService.findByUserId(#userId).username == principal.username")
    public ResponseEntity<List<Todo>> getActiveTodosById(@PathVariable Long userId) {
        UserEntity user = userService.findByUserId(userId);

        // TodoService를 사용하여 특정 ID의 할 일 항목을 가져옵니다.
        List<Todo> todoList = todoService.getActiveTodos(user);

        if (todoList != null) {
            return new ResponseEntity<>(todoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 정적 할 일 조회
    @GetMapping("/json/static")
    @PreAuthorize("hasRole('ROLE_USER') and userService.findByUserId(#userId).username == principal.username")
    public ResponseEntity<List<Todo>> getStaticTodosById(@PathVariable Long userId) {
        UserEntity user = userService.findByUserId(userId);

        // TodoService를 사용하여 특정 ID의 할 일 항목을 가져옵니다.
        List<Todo> todoList = todoService.getStaticTodos(user);

        if (todoList != null) {
            return new ResponseEntity<>(todoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 완료한 할 일 조회
    @GetMapping("/json/completed")
    @PreAuthorize("hasRole('ROLE_USER') and userService.findByUserId(#userId).username == principal.username")
    public ResponseEntity<List<Todo>> getCompletedTodosById(@PathVariable Long userId) {
        UserEntity user = userService.findByUserId(userId);

        // TodoService를 사용하여 특정 ID의 할 일 항목을 가져옵니다.
        List<Todo> todoList = todoService.getCompletedTodos(user);

        if (todoList != null) {
            return new ResponseEntity<>(todoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // 카테고리별 할 일 조회
    @GetMapping("/json/category?categoryName={category}")
    @PreAuthorize("hasRole('ROLE_USER') and userService.findByUserId(#userId).username == principal.username")
    public ResponseEntity<List<Todo>> getTodosbyIdAndCategory(@PathVariable Long userId, @PathVariable String category) {
        UserEntity user = userService.findByUserId(userId);

        List<Todo> todoList = todoService.getTodosByCategory(user, category);

        if (todoList != null) {
            return new ResponseEntity<>(todoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<Todo> updateTodo(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long taskId,
            @RequestBody Todo updatedTodoEntity
    ) {
        if(userDetails == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserEntity user = userService.findByUsername(username);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedTodoEntity.setTodoCategory(new TodoCategory(updatedTodoEntity.getCategoryName()));
        // TODO: 사용자 권한 검사
        Todo result = todoService.updateTodo(taskId, updatedTodoEntity);

        // 업데이트 실패 시
//        if(result == null) {
//            System.err.println("update to-do failed. updateTodo returned null.");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // todo: 권한 확인 필요
    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Void> deleteTodo(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long taskId
    ) {
        if(userDetails == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserEntity user = userService.findByUsername(username);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean isDeleted = todoService.deleteTodo(taskId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


