package com.devsuperior.movieflix.genre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.genre.dto.GenreDTO;
import com.devsuperior.movieflix.genre.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

	@Query("""
			SELECT new com.devsuperior.movieflix.genre.dto.GenreDTO(genre.genreId, genre.name)
			FROM Genre genre
			ORDER BY genre.genreId
			""")
	List<GenreDTO> listAll();

}
