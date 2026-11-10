package com.devsuperior.movieflix.movie.dto;

import com.devsuperior.movieflix.genre.dto.GenreDTO;
import com.devsuperior.movieflix.movie.entity.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;

public record MovieDetailsDTO(
		@JsonProperty("id")
		Long movieId,
		String title,
		String subTitle,
		@JsonProperty("year")
		Integer movieYear,
		@JsonProperty("imgUrl")
		String imageUrl,
		String synopsis,
		GenreDTO genre) {

	public static MovieDetailsDTO from(Movie movie) {
		return new MovieDetailsDTO(
				movie.getMovieId(),
				movie.getTitle(),
				movie.getSubTitle(),
				movie.getMovieYear(),
				movie.getImageUrl(),
				movie.getSynopsis(),
				GenreDTO.from(movie.getGenre()));
	}

}
