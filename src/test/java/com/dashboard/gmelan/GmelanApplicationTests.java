//package com.dashboard.gmelan;
//
//
//import com.dashboard.gmelan.todo.entity.Todo;
//import com.dashboard.gmelan.todo.repository.TodoCategoryRepository;
//import com.dashboard.gmelan.todo.service.TodoService;
//import com.dashboard.gmelan.user.Entity.UserEntity;
//import com.dashboard.gmelan.user.service.UserService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//@SpringBootTest
//class GmelanApplicationTests {
//	@Autowired
//	TodoService todoService;
//
//	@Autowired
//	UserService userService;
//
//	@Autowired
//	TodoCategoryRepository todoCategoryRepository;
//
//	@DisplayName("새 todo 추가")
//	@Test
//	public void createTodo() {
//		Timestamp further_past = new Timestamp(System.currentTimeMillis() - 100000000000L);
//		Timestamp past = new Timestamp(System.currentTimeMillis() - 100000000L);
//		Timestamp current = new Timestamp(System.currentTimeMillis());
//		Timestamp future = new Timestamp(System.currentTimeMillis() + 100000000L);
//		Timestamp further_future = new Timestamp(System.currentTimeMillis() + 10000000000L);
//		UserEntity user = userService.findByUsername("user");
//
//		Todo t = new Todo();
//		t.setUser(user);
//		t.setContent("할 일");
//		t.setTitle("미래에 시작하는 기한 있는 할 일 만들기 테스트");
//		t.setStartDate(future);
//		t.setEndDate(further_future);
//		todoService.createTodo(t, "진행 전");
//
//		t = new Todo();
//		t.setUser(user);
//		t.setContent("할 일");
//		t.setTitle("미래에 시작하는 기한 없는 할 일 만들기 테스트");
//		t.setStartDate(future);
//		todoService.createTodo(t, "진행 전");
//
//		t = new Todo();
//		t.setUser(user);
//		t.setContent("하는 중인 일");
//		t.setTitle("기한이 있는 하는 중인 일 만들기 테스트");
//		t.setStartDate(current);
//		t.setEndDate(future);
//		todoService.createTodo(t, "진행 중");
//
//		t = new Todo();
//		t.setUser(user);
//		t.setContent("하는 중인 일");
//		t.setTitle("기한이 없는 하는 중인 일 만들기 테스트");
//		t.setStartDate(current);
//		todoService.createTodo(t, "진행 중");
//
//		t = new Todo();
//		t.setUser(user);
//		t.setContent("한 일");
//		t.setTitle("기한이 있는 한 일 만들기 테스트");
//		t.setStartDate(further_past);
//		t.setEndDate(past);
//		todoService.createTodo(t, "진행 완료");
//	}
//
//	@Test
//	@DisplayName("Todo 수정")
//	public void updateTodo() {
//		UserEntity user = userService.findByUsername("user");
//		List<Todo> s = todoService.getActiveTodos(user);
//
//		for(Todo i : s) {
//			System.out.println("\n\n");
//			System.out.println(i.getTitle());
//			System.out.println(i.getContent());
//			System.out.println(i.getUser().getUsername());
//			System.out.println(i.getTodoCategoryEntity().getName());
//			System.out.println("\n\n");
//		}
//
//		Todo t = todoService.getActiveTodos(user).get(0);
//		System.out.println(t.getTitle() + "를 다음으로 변경: " + t.getTitle() + " 수정");
//		t.setTitle(t.getTitle() + " 수정");
//
//		todoService.updateTodo(t.getId(), t);
//
//		s = todoService.getActiveTodos(user);
//		for(Todo i : s) {
//			System.out.println("\n\n");
//			System.out.println(i.getTitle());
//			System.out.println(i.getContent());
//			System.out.println(i.getUser().getUsername());
//			System.out.println(i.getTodoCategoryEntity().getName());
//			System.out.println("\n\n");
//		}
//	}
//
//	@Test
//	@DisplayName("조건에 맞는 Todo 가져오기")
//	public void getTodosByCondition() {
//		UserEntity user = userService.findByUsername("user");
//		System.out.println("현재 하는 중인 일 목록");
//		for(Todo i: todoService.getActiveTodos(user)) {
//			System.out.println("\n\n");
//			System.out.println(i.getTitle());
//			System.out.println(i.getContent());
//			System.out.println(i.getUser().getUsername());
//			System.out.println(i.getTodoCategoryEntity().getName());
//			System.out.println("\n\n");
//		}
//	}
//
//	@Test
//	@DisplayName("Todo 삭제")
//	public void deleteTodo() {
//		UserEntity user = userService.findByUsername("user");
//		System.out.println("현재 하는 중인 일들만 삭제");
//
//		List<Todo> s = todoService.getActiveTodos(user);
//		for(Todo i : s) {
//			todoService.deleteTodo(i.getId());
//		}
//
//		s = todoService.getActiveTodos(user);
//		if(s.isEmpty()) {
//			System.out.println("텅");
//		}
//
//
//
//
//	}
//}
