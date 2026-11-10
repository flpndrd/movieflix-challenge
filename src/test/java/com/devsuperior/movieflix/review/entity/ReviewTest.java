package com.devsuperior.movieflix.review.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.devsuperior.movieflix.genre.entity.Genre;
import com.devsuperior.movieflix.movie.entity.Movie;
import com.devsuperior.movieflix.user.entity.User;

class ReviewTest {

	@Test
	void reviewShouldHaveCorrectStructure() {
		Movie movie = new Movie("Title", "Subtitle", 2021, "https://imgurl.com", "Synopsis", new Genre("Genre"));
		Review entity = new Review("Text", movie, new User("Name", "email@gmail.com", "password"));
		ReflectionTestUtils.setField(entity, "reviewId", 1L);

		Assertions.assertNotNull(entity.getReviewId());
		Assertions.assertNotNull(entity.getText());
		Assertions.assertNotNull(entity.getMovie());
		Assertions.assertNotNull(entity.getUser());
	}

}
