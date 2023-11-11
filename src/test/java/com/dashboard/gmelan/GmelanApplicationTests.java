package com.dashboard.gmelan;

import com.dashboard.gmelan.todo.entity.TodoCategoryEntity;
import com.dashboard.gmelan.todo.entity.TodoEntity;
import com.dashboard.gmelan.todo.repository.TodoCategoryRepository;
import com.dashboard.gmelan.todo.service.TodoService;
import com.dashboard.gmelan.user.Entity.UserEntity;
import com.dashboard.gmelan.user.Entity.UserPermissionEntity;
import com.dashboard.gmelan.user.repository.UserRepository;
import com.dashboard.gmelan.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
class GmelanApplicationTests {
	@Autowired
	TodoService todoService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TodoCategoryRepository todoCategoryRepository;

	@DisplayName("새 todo 추가")
	@Test
	public void createTodo() {
//		TodoEntity t = new TodoEntity();
//		t.setUser(userRepository.getReferenceById(1L)); // test
//		t.setContent("새 할 일");
//		t.setTitle("테스트");
//		t.setStartDate(new Timestamp(System.currentTimeMillis()));
//
//		todoService.createTodo(t, "진행 전");
//
//		List<TodoEntity> s = todoService.getActiveTodos(1L);
//		for(TodoEntity i : s) {
//			System.out.println("\n\n\n\n");
//			System.out.println(i.getTitle());
//			System.out.println(i.getTodoCategoryEntity().getName());
//			System.out.println(i.getUser().getUsername());
//			System.out.println(i.getContent());
//			System.out.println("\n\n\n\n");
//		}
//
//		t.setTitle("수정수정");
//		todoService.updateTodo(t.getId(), t);

		List<TodoEntity> s = todoService.getActiveTodos(1L);
		System.out.println("\n\n\n\n");
		for(TodoEntity i : s) {
			System.out.println(i.getTitle());
			System.out.println(i.getTodoCategoryEntity().getName());
			System.out.println(i.getUser().getUsername());
			System.out.println(i.getContent());
			System.out.println("\n");
		}
		System.out.println("\n\n\n\n");
	}
}
