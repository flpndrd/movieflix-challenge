package com.devsuperior.movieflix.review.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.accesstoken.AccessTokenSupport;
import com.devsuperior.movieflix.review.dto.ReviewDTO;

import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ReviewControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AccessTokenSupport accessTokenSupport;

	@Autowired
	private ObjectMapper objectMapper;

	private String visitorUsername;

	private String visitorPassword;

	private String memberUsername;

	private String memberPassword;

	@BeforeEach
	void setUp() throws Exception {
		visitorUsername = "bob@gmail.com";
		visitorPassword = "123456";
		memberUsername = "ana@gmail.com";
		memberPassword = "123456";
	}

	@Test
	void insertShouldReturnUnauthorizedWhenNotValidToken() throws Exception {
		ReviewDTO reviewDTO = new ReviewDTO(null, "Gostei do filme!", 1L, null, null, null);
		String jsonBody = objectMapper.writeValueAsString(reviewDTO);

		ResultActions result = mockMvc.perform(post("/reviews")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isUnauthorized());
	}

	@Test
	void insertShouldReturnForbiddenWhenVisitorAuthenticated() throws Exception {
		String accessToken = accessTokenSupport.obtainAccessToken(mockMvc, visitorUsername, visitorPassword);
		ReviewDTO reviewDTO = new ReviewDTO(null, "Gostei do filme!", 1L, null, null, null);
		String jsonBody = objectMapper.writeValueAsString(reviewDTO);

		ResultActions result = mockMvc.perform(post("/reviews")
				.header("Authorization", "Bearer " + accessToken)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isForbidden());
	}

	@Test
	void insertShouldInsertReviewWhenMemberAuthenticatedAndValidData() throws Exception {
		String accessToken = accessTokenSupport.obtainAccessToken(mockMvc, memberUsername, memberPassword);
		String reviewText = "Gostei do filme!";
		long movieId = 1L;
		ReviewDTO reviewDTO = new ReviewDTO(null, reviewText, movieId, null, null, null);
		String jsonBody = objectMapper.writeValueAsString(reviewDTO);

		ResultActions result = mockMvc.perform(post("/reviews")
				.header("Authorization", "Bearer " + accessToken)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").isNotEmpty());
		result.andExpect(jsonPath("$.text").value(reviewText));
		result.andExpect(jsonPath("$.movieId").value(movieId));
		result.andExpect(jsonPath("$.userId").isNotEmpty());
		result.andExpect(jsonPath("$.userName").isNotEmpty());
		result.andExpect(jsonPath("$.userEmail").value(memberUsername));
	}

	@Test
	void insertShouldReturnUnprocessableContentWhenMemberAuthenticatedAndInvalidData() throws Exception {
		String accessToken = accessTokenSupport.obtainAccessToken(mockMvc, memberUsername, memberPassword);
		ReviewDTO reviewDTO = new ReviewDTO(null, "        ", 1L, null, null, null);
		String jsonBody = objectMapper.writeValueAsString(reviewDTO);

		ResultActions result = mockMvc.perform(post("/reviews")
				.header("Authorization", "Bearer " + accessToken)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isUnprocessableContent());
	}

}
