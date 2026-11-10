package com.devsuperior.movieflix.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.user.dto.UserDTO;
import com.devsuperior.movieflix.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("""
			SELECT user
			FROM User user
			LEFT JOIN FETCH user.roles
			WHERE user.email = :email
			""")
	Optional<User> findWithRolesByEmail(String email);

	Optional<User> findByEmail(String email);

	@Query("""
			SELECT new com.devsuperior.movieflix.user.dto.UserDTO(user.userId, user.name, user.email)
			FROM User user
			WHERE user.email = :email
			""")
	Optional<UserDTO> findDTOByEmail(String email);

}
