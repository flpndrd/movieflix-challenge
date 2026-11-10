package com.devsuperior.movieflix.movie.entity;

import java.util.ArrayList;
import java.util.List;

import com.devsuperior.movieflix.genre.entity.Genre;
import com.devsuperior.movieflix.review.entity.Review;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long movieId;

	private String title;

	private String subTitle;

	private Integer movieYear;

	private String imageUrl;

	@Column(columnDefinition = "TEXT")
	private String synopsis;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "genre_id")
	private Genre genre;

	@OneToMany(mappedBy = "movie")
	private List<Review> reviews = new ArrayList<>();

	protected Movie() {
	}

	public Movie(String title, String subTitle, Integer movieYear, String imageUrl, String synopsis, Genre genre) {
		this.title = title;
		this.subTitle = subTitle;
		this.movieYear = movieYear;
		this.imageUrl = imageUrl;
		this.synopsis = synopsis;
		this.genre = genre;
	}

	public Long getMovieId() {
		return movieId;
	}

	public String getTitle() {
		return title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public Integer getMovieYear() {
		return movieYear;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public Genre getGenre() {
		return genre;
	}

	public List<Review> getReviews() {
		return reviews;
	}

}
