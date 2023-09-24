package com.dashboard.gmelan.controller;

import com.dashboard.gmelan.Entities.Todo;
import com.dashboard.gmelan.service.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dashboard.gmelan.Entities.Todo;
import com.dashboard.gmelan.service.TodoService;

import java.util.List;


@Service
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoRepository todoRepository;

    /**
     * Get todoList
     */
    @GetMapping("/{userId}")
    public String todoList(@PathVariable Long userId, Model model) {
        List<Todo> todoList = todoService.getTodoListByUserId(userId);

        model.addAttribute("todoList", todoList);
        return "todo";
    }

//    /**
//     * delete todoList
//     */
//    @DeleteMapping("/api/todo/{task_id}")
//    public ResponseBody<Void> deleteTask(@PathVariable Long taskId) {
//        todoRepository.deleteById(taskId);
//        return ResponseBody.noContent().bulid();
//    }

}
