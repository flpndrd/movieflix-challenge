package com.devsuperior.movieflix.movie.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.devsuperior.movieflix.genre.entity.Genre;

class MovieTest {

	@Test
	void movieShouldHaveCorrectStructure() {
		Movie entity = new Movie("Title", "Subtitle", 2021, "https://imgurl.com", "Synopsis", new Genre("Genre"));
		ReflectionTestUtils.setField(entity, "movieId", 1L);

		Assertions.assertNotNull(entity.getMovieId());
		Assertions.assertNotNull(entity.getTitle());
		Assertions.assertNotNull(entity.getSubTitle());
		Assertions.assertNotNull(entity.getSynopsis());
		Assertions.assertNotNull(entity.getMovieYear());
		Assertions.assertNotNull(entity.getImageUrl());
		Assertions.assertNotNull(entity.getGenre());
		Assertions.assertEquals(0, entity.getReviews().size());
	}

}
