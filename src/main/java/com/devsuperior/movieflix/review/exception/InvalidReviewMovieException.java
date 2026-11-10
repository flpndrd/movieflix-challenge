package com.devsuperior.movieflix.review.exception;

import java.io.Serial;

import com.devsuperior.movieflix.exception.InvalidRequestException;

public class InvalidReviewMovieException extends InvalidRequestException {

	@Serial
	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "Review has a movie that does not exist.";

	public InvalidReviewMovieException() {
		super(MESSAGE);
	}

}
