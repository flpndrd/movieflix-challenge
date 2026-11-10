package com.devsuperior.movieflix.genre.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.genre.dto.GenreDTO;
import com.devsuperior.movieflix.genre.repository.GenreRepository;

@Service
public class GenreService {

	private final GenreRepository genreRepository;

	public GenreService(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@Transactional(readOnly = true)
	public List<GenreDTO> list() {
		return genreRepository.listAll();
	}

}
