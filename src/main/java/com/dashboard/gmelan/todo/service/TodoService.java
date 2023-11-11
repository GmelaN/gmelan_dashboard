package com.dashboard.gmelan.todo.service;

import com.dashboard.gmelan.todo.entity.TodoCategoryEntity;
import com.dashboard.gmelan.todo.entity.TodoEntity;
import com.dashboard.gmelan.todo.repository.TodoCategoryRepository;
import com.dashboard.gmelan.todo.repository.TodoRepository;
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
    public List<TodoEntity> getActiveTodos(Long userId) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        // startDate가 null이거나 현재 시간 이전인 TodoEntity를 찾는다.
        List<TodoEntity> startBeforeNow = todoRepository.findByUserIdAndStartDateIsNullOrStartDateBefore(userId, now);

        // 찾은 TodoEntity에서 endDate가 null이거나 현재 시간 이후인 TodoEntity를 찾는다.
        List<TodoEntity> activeTodos = startBeforeNow.stream()
                .filter(todo -> todo.getEndDate() == null || todo.getEndDate().after(now))
                .collect(Collectors.toList());

        return activeTodos;
    }

    public List<TodoEntity> getActiveTodosByCategory(Long userId, String category) {
        List<TodoEntity> result = new ArrayList<>();

        // 먼저 카테고리의 ID를 가져온다.
        TodoCategoryEntity s = todoCategoryRepository.findByName(category);
        if(s == null) {
            return result;
        }

        // 그 다음 가져온 ID들을 기반으로 리스트를 조회한다.
        result = todoRepository.findByUserIdAndTodoCategoryEntity(userId, s);
        return result;
    }

    public List<TodoEntity> getAllTodos(Long userId) {
        // 사용자의 모든 리스트를 가져온다.
        return todoRepository.findByUserId(userId);
    }


    /* 할 일 만들기 */
    public TodoEntity createTodo(TodoEntity todoEntity, String category) {
        todoEntity.setCategoryName(category);

        // 새 할 일에 있는 카테고리가 신규 카테고리인지 확인
        TodoCategoryEntity categoryEntity = getOrCreateCategory(category);

        // 이제 할 일을 담아도 됨
        todoEntity.setTodoCategoryEntity(categoryEntity);

        return todoRepository.save(todoEntity);
    }

    /* 할 일 제거하기 */

    public void deleteTodo(Long taskId) {
        // delete시 해당 todoCategory를 참조하는 _todo가 하나도 없는 경우 category도 함께 제거

        // 먼저 해당 todoEntity가 있는지 보자.
        TodoEntity todoEntity = todoRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + taskId));

        // 일단 DB에서 지우고
        todoRepository.delete(todoEntity);

        // 지운 놈의 카테고리를 갖는 _todo의 수를 조사
        Long categoryCount = todoRepository.countByTodoCategoryEntity(todoEntity.getTodoCategoryEntity());

        // 더이상 그 카테고리가 필요하지 않는 경우 drop
        if (categoryCount == 0)
            todoCategoryRepository.deleteById(todoEntity.getTodoCategoryEntity().getId());
    }


    /* 할 일 수정하기 */
    public TodoEntity updateTodo(Long taskId, TodoEntity updatedTodoEntity) {
        // 먼저 해당 todoEntity가 있는지 보자.
        TodoEntity todoEntity = todoRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + taskId));

        // 가져온 할 일의 속성 변경 - userId는 제외
        todoEntity.setTitle(updatedTodoEntity.getTitle());
        todoEntity.setContent(updatedTodoEntity.getContent());
        todoEntity.setStartDate(updatedTodoEntity.getStartDate());
        todoEntity.setEndDate(updatedTodoEntity.getEndDate());
        todoEntity.setUrl(updatedTodoEntity.getUrl());

        // 카테고리 업데이트
        String category = updatedTodoEntity.getTodoCategoryEntity().getName();
        
        TodoCategoryEntity categoryEntity = getOrCreateCategory(category);
        todoEntity.setTodoCategoryEntity(categoryEntity);

        // 수정된 할 일 저장
        return todoRepository.save(todoEntity);
    }
    
    /* 내부 메서드 */

    /**
     * 카테고리가 신규 카테고리인지 확인하고, 신규 카테고리라면 DB에 추가
     */
    private TodoCategoryEntity getOrCreateCategory(String categoryName) {
        if(categoryName == null || categoryName.isBlank()) categoryName = "분류 없음";

        // 먼저 카테고리가 신규 카테고리인지 확인해보자.
        TodoCategoryEntity categoryEntity = todoCategoryRepository.findByName(categoryName);

        // 신규 카테고리인 경우 null일 것임
        if (categoryEntity == null) {
            categoryEntity = new TodoCategoryEntity();

            // 신규 카테고리를 카테고리 테이블에 저장
            categoryEntity.setName(categoryName);
            categoryEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            todoCategoryRepository.save(categoryEntity);

            return categoryEntity;
        }
        return categoryEntity;
    }
}