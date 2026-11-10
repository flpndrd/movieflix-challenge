package com.devsuperior.movieflix.movie.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.movie.dto.MovieCardDTO;
import com.devsuperior.movieflix.movie.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.movie.service.MovieService;
import com.devsuperior.movieflix.review.dto.ReviewDTO;
import com.devsuperior.movieflix.review.service.ReviewService;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private final MovieService movieService;

	private final ReviewService reviewService;

	public MovieController(MovieService movieService, ReviewService reviewService) {
		this.movieService = movieService;
		this.reviewService = reviewService;
	}

	@GetMapping("/{movieId}")
	ResponseEntity<MovieDetailsDTO> find(@PathVariable Long movieId) {
		MovieDetailsDTO movieDetailsDTO = movieService.find(movieId);
		return ResponseEntity.ok(movieDetailsDTO);
	}

	@GetMapping
	ResponseEntity<Page<MovieCardDTO>> list(@RequestParam(defaultValue = "0") Long genreId, Pageable pageable) {
		Page<MovieCardDTO> movieCardPage = movieService.list(genreId, pageable);
		return ResponseEntity.ok(movieCardPage);
	}

	@GetMapping("/{movieId}/reviews")
	ResponseEntity<List<ReviewDTO>> listReviews(@PathVariable Long movieId) {
		List<ReviewDTO> reviews = reviewService.listByMovie(movieId);
		return ResponseEntity.ok(reviews);
	}

}
