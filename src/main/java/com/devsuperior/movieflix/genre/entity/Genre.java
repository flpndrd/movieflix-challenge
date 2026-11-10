package com.devsuperior.movieflix.genre.entity;

import java.util.ArrayList;
import java.util.List;

import com.devsuperior.movieflix.movie.entity.Movie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_genre")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long genreId;

	private String name;

	@OneToMany(mappedBy = "genre")
	private List<Movie> movies = new ArrayList<>();

	protected Genre() {
	}

	public Genre(String name) {
		this.name = name;
	}

	public Long getGenreId() {
		return genreId;
	}

	public String getName() {
		return name;
	}

	public List<Movie> getMovies() {
		return movies;
	}

}
