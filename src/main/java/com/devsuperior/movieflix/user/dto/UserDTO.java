package com.devsuperior.movieflix.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDTO(
		@JsonProperty("id")
		Long userId,
		String name,
		String email) {

}
