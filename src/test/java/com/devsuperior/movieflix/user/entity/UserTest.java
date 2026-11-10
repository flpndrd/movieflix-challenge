package com.devsuperior.movieflix.user.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class UserTest {

	@Test
	void userShouldHaveCorrectStructure() {
		User entity = new User("Name", "email@gmail.com", "password");
		ReflectionTestUtils.setField(entity, "userId", 1L);

		Assertions.assertNotNull(entity.getUserId());
		Assertions.assertNotNull(entity.getName());
		Assertions.assertNotNull(entity.getEmail());
		Assertions.assertNotNull(entity.getPassword());
		Assertions.assertEquals(0, entity.getRoles().size());
		Assertions.assertEquals(0, entity.getReviews().size());
	}

}
