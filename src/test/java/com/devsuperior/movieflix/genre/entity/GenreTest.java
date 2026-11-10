package com.devsuperior.movieflix.genre.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class GenreTest {

	@Test
	void genreShouldHaveCorrectStructure() {
		Genre entity = new Genre("Maria");
		ReflectionTestUtils.setField(entity, "genreId", 1L);

		Assertions.assertNotNull(entity.getGenreId());
		Assertions.assertNotNull(entity.getName());
		Assertions.assertEquals(0, entity.getMovies().size());
	}

}
