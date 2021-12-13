package com.bae.backend.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.backend.entity.Plants;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up the mockmvc object
@Sql(scripts = {"classpath:plant-schema.sql", "classpath:plant-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class PlantsControllerTest {
	
	@Autowired
	private MockMvc mvc; 
	
	@Autowired
	private ObjectMapper mapper;
	
	
	@Test 
	void testCreate() throws Exception {
		Plants plant = new Plants( "Daffodil", "green", "October", "sun","Yellow");
		String plantAsJSON = this.mapper.writeValueAsString(plant);
		
		RequestBuilder req = post("/createPlant").contentType(MediaType.APPLICATION_JSON).content(plantAsJSON);
		
		Plants testPlant = new Plants(4, "Daffodil", "green", "October", "sun","Yellow");
		String testPlantAsJSON = this.mapper.writeValueAsString(testPlant);		
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testPlantAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetAll() throws Exception {
		List<Plants> plant = List.of(new Plants(1, "Daffodil", "green", "October", "sun","Yellow"),
				new Plants(2, "Peony", "green", "October", "sun","Pink"),
				new Plants(3, "Marigold", "green", "March", "sun","Orange"));
		String plantAsJSON = this.mapper.writeValueAsString(plant);
		
		RequestBuilder req = post("/getAll");
			
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(plantAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
		
	}
	

//	@GetMapping("getAll")
//	
//	
//	@GetMapping("/getPlantById/{id}")
//	
//	
//	@GetMapping("/getPlantByName/{name}")
//	
//	@GetMapping("/getPlantByMonth/{month}")
//	
//	
//	@PutMapping("/updatePlant/{id}") 
//	
//	@DeleteMapping("/deletePlant/{id}") 
	

}
