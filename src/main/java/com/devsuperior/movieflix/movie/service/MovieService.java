package com.devsuperior.movieflix.movie.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.movie.dto.MovieCardDTO;
import com.devsuperior.movieflix.movie.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.movie.entity.Movie;
import com.devsuperior.movieflix.movie.exception.MovieNotFoundException;
import com.devsuperior.movieflix.movie.repository.MovieRepository;

@Service
public class MovieService {

	private final MovieRepository movieRepository;

	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Transactional(readOnly = true)
	public MovieDetailsDTO find(Long movieId) {
		Movie movie = movieRepository.findWithGenreByMovieId(movieId).orElseThrow(() -> new MovieNotFoundException());
		return MovieDetailsDTO.from(movie);
	}

	@Transactional(readOnly = true)
	public Page<MovieCardDTO> list(Long genreId, Pageable pageable) {
		return movieRepository.listByGenre(genreId, pageable);
	}

}
