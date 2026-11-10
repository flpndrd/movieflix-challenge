package com.devsuperior.movieflix.review.dto;

import com.devsuperior.movieflix.review.entity.Review;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewDTO(
		@JsonProperty("id")
		Long reviewId,
		@NotBlank(message = "Field is required")
		String text,
		@NotNull(message = "Field is required")
		Long movieId,
		Long userId,
		String userName,
		String userEmail) {

	public static ReviewDTO from(Review review) {
		return new ReviewDTO(
				review.getReviewId(),
				review.getText(),
				review.getMovie().getMovieId(),
				review.getUser().getUserId(),
				review.getUser().getName(),
				review.getUser().getEmail());
	}

}
