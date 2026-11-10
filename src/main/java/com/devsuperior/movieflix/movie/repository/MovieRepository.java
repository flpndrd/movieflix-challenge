package com.devsuperior.movieflix.movie.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.movie.dto.MovieCardDTO;
import com.devsuperior.movieflix.movie.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query("""
			SELECT new com.devsuperior.movieflix.movie.dto.MovieCardDTO(
				movie.movieId,
				movie.title,
				movie.subTitle,
				movie.movieYear,
				movie.imageUrl)
			FROM Movie movie
			WHERE :genreId = 0
			OR movie.genre.genreId = :genreId
			ORDER BY movie.title
			""")
	Page<MovieCardDTO> listByGenre(Long genreId, Pageable pageable);

	@Query("""
			SELECT movie
			FROM Movie movie
			JOIN FETCH movie.genre
			WHERE movie.movieId = :movieId
			""")
	Optional<Movie> findWithGenreByMovieId(Long movieId);

}
