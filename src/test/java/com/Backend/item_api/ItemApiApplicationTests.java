package com.Backend.item_api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.Backend.item_api.service.ItemService;

@SpringBootTest
@AutoConfigureMockMvc
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

		mockMvc.perform(post("/api/v1/items")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
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

		MvcResult createResult = mockMvc.perform(post("/api/v1/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isOk())
			.andReturn();

		Number itemIdValue = JsonPath.read(createResult.getResponse().getContentAsString(), "$.id");
		long itemId = itemIdValue.longValue();

		mockMvc.perform(get("/api/v1/items/" + itemId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(itemId))
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

		mockMvc.perform(post("/api/v1/items")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.fieldErrors.name").value("name is required"));
	}

}
