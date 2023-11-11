package com.dashboard.gmelan;

import com.dashboard.gmelan.user.Entity.UserEntity;
import com.dashboard.gmelan.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
class GmelanApplicationTests {
	@Autowired
	UserService userService;

	@DisplayName("새 사용자 추가")
	@Test
	public void createNewUser() {
		userService.create("a", "a@a.a", "a", "a");
	}
}
