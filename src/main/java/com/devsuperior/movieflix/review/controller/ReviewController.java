package com.devsuperior.movieflix.review.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.review.dto.ReviewDTO;
import com.devsuperior.movieflix.review.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@PostMapping
	ResponseEntity<ReviewDTO> create(@Valid @RequestBody ReviewDTO reviewDTO) {
		ReviewDTO createdReviewDTO = reviewService.create(reviewDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdReviewDTO);
	}

}
