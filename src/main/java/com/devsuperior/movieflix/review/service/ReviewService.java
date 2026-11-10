package com.devsuperior.movieflix.review.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.movie.entity.Movie;
import com.devsuperior.movieflix.movie.exception.MovieNotFoundException;
import com.devsuperior.movieflix.movie.repository.MovieRepository;
import com.devsuperior.movieflix.review.dto.ReviewDTO;
import com.devsuperior.movieflix.review.entity.Review;
import com.devsuperior.movieflix.review.exception.InvalidReviewMovieException;
import com.devsuperior.movieflix.review.repository.ReviewRepository;
import com.devsuperior.movieflix.security.AuthenticatedUser;
import com.devsuperior.movieflix.user.entity.User;
import com.devsuperior.movieflix.user.exception.UserNotFoundException;
import com.devsuperior.movieflix.user.repository.UserRepository;

@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;

	private final MovieRepository movieRepository;

	private final UserRepository userRepository;

	private final AuthenticatedUser authenticatedUser;

	public ReviewService(
			ReviewRepository reviewRepository,
			MovieRepository movieRepository,
			UserRepository userRepository,
			AuthenticatedUser authenticatedUser) {
		this.reviewRepository = reviewRepository;
		this.movieRepository = movieRepository;
		this.userRepository = userRepository;
		this.authenticatedUser = authenticatedUser;
	}

	@Transactional(readOnly = true)
	public List<ReviewDTO> listByMovie(Long movieId) {
		if (!movieRepository.existsById(movieId)) {
			throw new MovieNotFoundException();
		}
		return reviewRepository.listByMovieId(movieId);
	}

	@Transactional
	public ReviewDTO create(ReviewDTO reviewDTO) {
		Movie movie = movieRepository
				.findById(reviewDTO.movieId())
				.orElseThrow(() -> new InvalidReviewMovieException());

		User user = userRepository
				.findByEmail(authenticatedUser.getEmail())
				.orElseThrow(() -> new UserNotFoundException());

		Review savedReview = reviewRepository.save(new Review(reviewDTO.text(), movie, user));
		return ReviewDTO.from(savedReview);
	}

}
