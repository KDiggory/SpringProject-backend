package com.bae.backend.exceptions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.bae.backend.entity.Plants;
import com.bae.backend.repo.PlantsRepo;
import com.bae.backend.service.PlantsService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CustomExceptionTesting {
	
	@MockBean 
	private PlantsRepo repo;
	
	@Autowired
	private PlantsService service;
	
	
	@Test  
	void testNoPlantID() {		
		Plants plant = new Plants();
		plant.setId(null);
		Mockito.when(this.repo.findById(1)).thenReturn(Optional.ofNullable(null));
		try {
			this.service.getByID(1);
		} catch (PlantsNotFoundException e) {
			assertThat(e.getMessage().equals("No plant found with that id"));
			return; // this stops it if the assert passes
		}
		fail(); // this will only be gotten to if the assertThat doesnt do what expected. thrown and not caught will be 500 - can tell spring to put them as a 404
	}
	
	@Test  // this doesnt work
	void testMonthNotFound() throws MonthNotFoundException {		
		Plants plant = new Plants(1, "Daffodil", "green", "October", "sun", "Yellow");
		Plants plant2 = new Plants(2, "Daffodil", "green", "October", "sun", "Yellow");
		List<Plants> plantList = new ArrayList();
		plantList.add(plant);
		plantList.add(plant2);
		
		Mockito.when(this.repo.save(plant)).thenReturn(plant);
		Mockito.when(this.repo.save(plant2)).thenReturn(plant2);
		Mockito.when(this.repo.getAllByPlantingMonth("November")).thenReturn(plantList);
		if (this.service.getSaved("November").isEmpty()) {
		try {
			this.service.getByPlantingMonth("November");
		} catch (MonthNotFoundException e) {
			assertThat(e.getMessage().equals("No plant found with that planting month"));
			return; 
		}
		fail(); 
	}
	}

}
