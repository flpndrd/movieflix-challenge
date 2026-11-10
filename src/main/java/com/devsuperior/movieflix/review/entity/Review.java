package com.devsuperior.movieflix.review.entity;

import com.devsuperior.movieflix.movie.entity.Movie;
import com.devsuperior.movieflix.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_review")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;

	@Column(columnDefinition = "TEXT")
	private String text;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	protected Review() {
	}

	public Review(String text, Movie movie, User user) {
		this.text = text;
		this.movie = movie;
		this.user = user;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public String getText() {
		return text;
	}

	public Movie getMovie() {
		return movie;
	}

	public User getUser() {
		return user;
	}

}
