package com.devsuperior.movieflix.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.review.dto.ReviewDTO;
import com.devsuperior.movieflix.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	@Query("""
			SELECT new com.devsuperior.movieflix.review.dto.ReviewDTO(
				review.reviewId,
				review.text,
				review.movie.movieId,
				user.userId,
				user.name,
				user.email)
			FROM Review review
			JOIN review.user user
			WHERE review.movie.movieId = :movieId
			ORDER BY review.reviewId
			""")
	List<ReviewDTO> listByMovieId(Long movieId);

}
