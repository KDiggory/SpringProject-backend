package com.bae.backend.exceptions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.bae.backend.entity.Plants;
import com.bae.backend.repo.PlantsRepo;
import com.bae.backend.service.PlantsService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up the mockmvc object
@Sql(scripts = { "classpath:plant-schema.sql",
		"classpath:plant-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class CustomExceptionTesting {
	
	@MockBean 
	private PlantsRepo repo;
	
	@MockBean
	private PlantsService service;
	
	@Rule
	public PlantsNotFoundException thrown = new PlantsNotFoundException();
	
	@Test
	void testNoPlantID() {		
		Plants plant = new Plants();
		plant.setId(null);
		Mockito.when(this.repo.findById(1)).thenThrow( new PlantsNotFoundException());
		
		assertThat(thrown.getMessage().equals("No plant found with that id"));
	}

}
