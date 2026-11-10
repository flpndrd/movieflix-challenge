package com.devsuperior.movieflix.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MovieCardDTO(
		@JsonProperty("id")
		Long movieId,
		String title,
		String subTitle,
		@JsonProperty("year")
		Integer movieYear,
		@JsonProperty("imgUrl")
		String imageUrl) {}
