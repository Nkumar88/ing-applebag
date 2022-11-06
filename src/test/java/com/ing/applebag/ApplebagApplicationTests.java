package com.ing.applebag;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
class ApplebagApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Order(1)
	public void testAGetBagsWhenRecordsLessThanThreeSuccess() throws Exception
	{
		mockMvc.perform(get("/getBags")
				.contentType(MediaType.APPLICATION_JSON))
		        .andDo(print())
				.andExpect(status().isPartialContent());
	}
	
	@Test
	public void testBGetBagsWhenRecordsEqualsOrMoreThanThreeSuccess() throws Exception
	{
		String request1 = "{\"numOfApples\":\"100\",\"supplier\":\"Elstar Apples\",\"price\":\"50\"}";
		String request2 = "{\"numOfApples\":\"99\",\"supplier\":\"Royal Gala\",\"price\":\"20\"}";
		String request3 = "{\"numOfApples\":\"50\",\"supplier\":\"Pink Lady\",\"price\":\"10\"}";
		mockMvc.perform(post("/addBag")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request1))
		        .andDo(print());
		mockMvc.perform(post("/addBag")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request2))
		        .andDo(print());
		mockMvc.perform(post("/addBag")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request3))
		        .andDo(print());
		
		mockMvc.perform(get("/getBags")
				.contentType(MediaType.APPLICATION_JSON))
		        .andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void testCGetBagsWithOptionalParameterSuccess() throws Exception
	{
		String request1 = "{\"numOfApples\":\"100\",\"supplier\":\"Elstar Apples\",\"price\":\"50\"}";
		String request2 = "{\"numOfApples\":\"99\",\"supplier\":\"Royal Gala\",\"price\":\"20\"}";
		mockMvc.perform(post("/addBag")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request1))
		        .andDo(print());
		mockMvc.perform(post("/addBag")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request2))
		        .andDo(print());
		
		mockMvc.perform(get("/getBags/1")
				.contentType(MediaType.APPLICATION_JSON))
		        .andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void testDGetBagsWithOptionalParameterInvalidInput() throws Exception
	{
		String request1 = "{\"numOfApples\":\"100\",\"supplier\":\"Elstar Apples\",\"price\":\"50\"}";
		String request2 = "{\"numOfApples\":\"99\",\"supplier\":\"Royal Gala\",\"price\":\"20\"}";
		mockMvc.perform(post("/addBag")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request1))
		        .andDo(print());
		mockMvc.perform(post("/addBag")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request2))
		        .andDo(print());
		
		mockMvc.perform(get("/getBags/0")
				.contentType(MediaType.APPLICATION_JSON))
		        .andDo(print())
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testEAddBagSuccess() throws Exception
	{
		String request = "{\"numOfApples\":\"10\",\"supplier\":\"Elstar Apples\",\"price\":\"40\"}";
		mockMvc.perform(post("/addBag")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void testFAddBagInvalidNumOfApples() throws Exception
	{
		String request = "{\"numOfApples\":\"101\",\"supplier\":\"Elstar Apples\",\"price\":\"40\"}";
		mockMvc.perform(post("/addBag")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request))
		        .andDo(print())
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testGAddBagInvalidSupplier() throws Exception
	{
		String request = "{\"numOfApples\":\"100\",\"supplier\":\"El Apples\",\"price\":\"40\"}";
		mockMvc.perform(post("/addBag")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request))
		        .andDo(print())
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testHAddBagInvalidPrice() throws Exception
	{
		String request = "{\"numOfApples\":\"100\",\"supplier\":\"Elstar Apples\",\"price\":\"51\"}";
		mockMvc.perform(post("/addBag")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request))
		        .andDo(print())
				.andExpect(status().isBadRequest());
	}
	
	
	
	

}
