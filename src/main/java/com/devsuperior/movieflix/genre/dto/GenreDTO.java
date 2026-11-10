package com.devsuperior.movieflix.genre.dto;

import com.devsuperior.movieflix.genre.entity.Genre;
import com.fasterxml.jackson.annotation.JsonProperty;

public record GenreDTO(
		@JsonProperty("id")
		Long genreId,
		String name) {

	public static GenreDTO from(Genre genre) {
		return new GenreDTO(genre.getGenreId(), genre.getName());
	}

}
