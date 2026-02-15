package com.Backend.item_api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.Backend.item_api.service.ItemService;

@SpringBootTest
@com.Backend.item_api.AutoConfigureMockMvc
class ItemApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ItemService itemService;

	@BeforeEach
	void resetDataStore() {
		itemService.clear();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void addItemReturnsCreated() throws Exception {
		String requestBody = """
				{
				  "name": "Laptop",
				  "description": "Thin and light laptop",
				  "category": "Electronics",
				  "price": 799.99
				}
				""";

		mockMvc.perform(post("/api/items")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Laptop"));
	}

	@Test
	void getItemByIdReturnsItem() throws Exception {
		String requestBody = """
				{
				  "name": "Movie Ticket",
				  "description": "Weekend show ticket",
				  "category": "Entertainment",
				  "price": 12.5
				}
				""";

		mockMvc.perform(post("/api/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isCreated());

		mockMvc.perform(get("/api/items/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.category").value("Entertainment"));
	}

	@Test
	void addItemWithMissingNameReturnsBadRequest() throws Exception {
		String requestBody = """
				{
				  "description": "No name item",
				  "category": "Test",
				  "price": 1.0
				}
				""";

		mockMvc.perform(post("/api/items")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.fieldErrors.name").value("name is required"));
	}

}
