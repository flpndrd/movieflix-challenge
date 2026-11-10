package com.devsuperior.movieflix.genre.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.genre.dto.GenreDTO;
import com.devsuperior.movieflix.genre.service.GenreService;

@RestController
@RequestMapping("/genres")
public class GenreController {

	private final GenreService genreService;

	public GenreController(GenreService genreService) {
		this.genreService = genreService;
	}

	@GetMapping
	ResponseEntity<List<GenreDTO>> list() {
		List<GenreDTO> genres = genreService.list();
		return ResponseEntity.ok(genres);
	}

}
