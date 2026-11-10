package com.devsuperior.movieflix.user.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class RoleTest {

	@Test
	void roleShouldHaveCorrectStructure() {
		Role entity = new Role("ROLE_MEMBER");
		ReflectionTestUtils.setField(entity, "roleId", 1L);

		Assertions.assertNotNull(entity.getRoleId());
		Assertions.assertNotNull(entity.getAuthority());
	}

}
