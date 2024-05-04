package com.dashboard.gmelan.service;

import com.dashboard.gmelan.entity.TodoCategory;
import com.dashboard.gmelan.entity.Todo;
import com.dashboard.gmelan.repository.TodoCategoryRepository;
import com.dashboard.gmelan.repository.TodoRepository;
import com.dashboard.gmelan.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoCategoryRepository todoCategoryRepository;

    public TodoService(TodoRepository todoRepository, TodoCategoryRepository todoCategoryRepository) {
        this.todoRepository = todoRepository;
        this.todoCategoryRepository = todoCategoryRepository;
    }


    /* 할 일 조회 */
    @Transactional(readOnly=true)
    public List<Todo> getActiveTodos(UserEntity user) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        // startDate가 null이거나 현재 시간 이전인 TodoEntity를 찾는다.
        List<Todo> startBeforeNow = todoRepository.findByUserIdAndStartDateIsNullOrStartDateBefore(user.getId(), now);

        // 찾은 TodoEntity에서 endDate가 null이거나 현재 시간 이후인 TodoEntity를 찾는다.
        List<Todo> activeTodos = startBeforeNow.stream()
                .filter(todo -> todo.getEndDate() == null || todo.getEndDate().after(now))
                .collect(Collectors.toList());

        return activeTodos;
    }

    @Transactional
    public Todo getTodoById(Long taskId) {
        return todoRepository.getReferenceById(taskId);
    }

    @Transactional(readOnly=true)
    public List<Todo> getStaticTodos(UserEntity user) {
        List<Todo> staticTodos = todoRepository.findByUserIdAndStartDateIsNullAndEndDateIsNull(user.getId());

        return staticTodos;
    }

    @Transactional(readOnly = true)
    public List<Todo> getCompletedTodos(UserEntity user) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        List<Todo> completedTodos = todoRepository.findByUserIdAndEndDateIsBefore(user.getId(), now);

        return  completedTodos;
    }

    @Transactional(readOnly=true)
    public List<Todo> getTodosByCategory(UserEntity user, String category) {
        List<Todo> result = new ArrayList<>();

        // 먼저 카테고리의 ID를 가져온다.
        TodoCategory s = todoCategoryRepository.findByName(category);
        if(s == null) {
            return null;
        }

        // 그 다음 가져온 ID들을 기반으로 리스트를 조회한다.
        result = todoRepository.findByUserIdAndTodoCategory(user.getId(), s);
        return result;
    }

    @Transactional(readOnly=true)
    public List<Todo> getAllTodos(UserEntity user) {
        // 사용자의 모든 리스트를 가져온다.
        return todoRepository.findByUserId(user.getId());
    }


    /* 할 일 만들기 */
    @Transactional
    public Todo createTodo(Todo todo) {
        if(!isValidDate(todo.getStartDate(), todo.getEndDate()))
            throw new IllegalArgumentException("invalid date range detected.");

        return todoRepository.save(todo);
    }

    /* 할 일 제거하기 */
    @Transactional
    public boolean deleteTodo(Long taskId) {
        // delete시 해당 todoCategory를 참조하는 _todo가 하나도 없는 경우 category도 함께 제거

        try {
            // 먼저 해당 todoEntity가 있는지 보자.
            Todo todoEntity = todoRepository.findById(taskId)
                    .orElseThrow(() -> new RuntimeException("Todo not found with id: " + taskId));

            // 일단 DB에서 지우고
            todoRepository.delete(todoEntity);

            // 지운 놈의 카테고리를 갖는 _todo의 수를 조사
            Long categoryCount = todoRepository.countByTodoCategory(todoEntity.getTodoCategory());

            // 더이상 그 카테고리가 필요하지 않는 경우 drop
            if (categoryCount == 0)
                todoCategoryRepository.deleteById(todoEntity.getTodoCategory().getId());
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }


    /* 할 일 수정하기 */
    @Transactional
    public Todo updateTodo(Long taskId, Todo updatedTodo) {
        // 먼저 해당 todo가 있는지 보자.
        Todo targetTodo = todoRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + taskId));

        // 날짜 범위의 유효성을 검사하자.
        if(!isValidDate(updatedTodo.getStartDate(), updatedTodo.getEndDate()))
            throw new IllegalArgumentException("invalid date range detected.");

        // 가져온 할 일의 속성 변경 - userId는 제외
        targetTodo.setTitle(updatedTodo.getTitle());
        targetTodo.setContent(updatedTodo.getContent());
        targetTodo.setStartDate(updatedTodo.getStartDate());
        targetTodo.setEndDate(updatedTodo.getEndDate());
        targetTodo.setUrl(updatedTodo.getUrl());

        // 카테고리 업데이트
        String categoryString = updatedTodo.getTodoCategory().getName();
        
        TodoCategory category = getOrCreateCategory(categoryString);
        targetTodo.setTodoCategory(category);

        targetTodo.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        // 수정된 할 일 저장
        return todoRepository.save(targetTodo);
    }
    
    /* 내부 메서드 */

    /**
     * 카테고리가 신규 카테고리인지 확인하고, 신규 카테고리라면 DB에 추가
     */
    public TodoCategory getOrCreateCategory(String categoryName) {
        if(categoryName == null || categoryName.isBlank()) {
            categoryName = "분류 없음";
        }

        // 먼저 카테고리가 신규 카테고리인지 확인해보자.
        TodoCategory category = todoCategoryRepository.findByName(categoryName);

        // 신규 카테고리인 경우 null일 것임
        if (category == null) {
            category = new TodoCategory(categoryName);

            // 신규 카테고리를 카테고리 테이블에 저장
            todoCategoryRepository.save(category);

            category = todoCategoryRepository.findByName(categoryName);
            return category;
        }
        return category;
    }

    /**
     * todoEntity의 날짜가 유효한 날짜 범위를 가지는지 확인
     */
    private boolean isValidDate(Timestamp startDate, Timestamp endDate) {
        if(startDate == null || endDate == null) {
            return true;
        }
        return !startDate.after(endDate);
    }
}