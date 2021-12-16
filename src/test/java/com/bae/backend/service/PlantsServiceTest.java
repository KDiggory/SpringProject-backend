
package com.bae.backend.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.bae.backend.entity.Plants;
import com.bae.backend.exceptions.PlantsNotFoundException;
import com.bae.backend.repo.PlantsRepo;

import antlr.Utils;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PlantsServiceTest {
	
	@MockBean 
	private PlantsRepo repo;
	
	@MockBean
	private PlantsService service;
	
	@Test
	void testContextLoads() throws Exception {
		assertThat(service).isNotNull();
	}
	
	@Test
	void testCreatePlant() { 
		Plants testPlant = new Plants(1, "Daffodil", "green", "October", "sun", "Yellow");
		Plants savedTestPlant = new Plants(1, "Daffodil", "green", "October", "sun", "Yellow");
		
		Mockito.when(this.repo.save(testPlant)).thenReturn(savedTestPlant);
		
				
		assertThat(this.service.createPlant(testPlant)).isEqualTo(savedTestPlant);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(testPlant);

	}
	
	@Test
	void testGetAllPlants() { 
		List<Plants> plant = List.of(new Plants(1, "Daffodil", "green", "October", "sun", "Yellow"),
				new Plants(2, "Peony", "green", "October", "sun", "Pink"),
				new Plants(3, "Marigold", "green", "March", "sun", "Orange"));
		
		Mockito.when(this.repo.findAll()).thenReturn(plant);
		
		assertThat(this.repo.findAll()).isEqualTo(plant);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
		
	}
	
	@Test
	void testGetByID() {
		Optional<Plants> plant = Optional.of(new Plants(1, "Daffodil", "green", "October", "sun", "Yellow"));
		
		Mockito.when(this.repo.findById(1)).thenReturn(plant);
		
		assertThat(this.repo.findById(1)).isEqualTo(plant);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(1);
		
}
	
	@Test
	void testGetByPlantingMonth() {
		List<Plants> plant = List.of(new Plants(1, "Daffodil", "green", "October", "sun", "Yellow"),
				new Plants(2, "Peony", "green", "October", "sun", "Pink"));
		
		Mockito.when(this.repo.getAllByPlantingMonth("October")).thenReturn(plant);
		
		assertThat(this.repo.getAllByPlantingMonth("October")).isEqualTo(plant);
		
		Mockito.verify(this.repo, Mockito.times(1)).getAllByPlantingMonth("October");
		
	}
	
	@Test
	void testGetByName() {
		Optional<Plants> plant = Optional.of(new Plants( "Daffodil", "green", "October", "sun", "Yellow"));
				
		Mockito.when(this.repo.getByName("Daffodil")).thenReturn(plant);
		
		assertThat(this.repo.getByName("Daffodil")).isEqualTo(plant);
		
		Mockito.verify(this.repo, Mockito.times(1)).getByName("Daffodil");
		
	}
	
	@Test
	void testUpdatePlant() throws PlantsNotFoundException { // is coming back as null - because it involves save?
		Optional<Plants> testPlant = Optional.of(new Plants(1,"Daffodil", "green", "October", "sun", "Yellow"));
		Plants testPlantUpdate = new Plants(1,"Narcissus", "green", "October", "sun", "Yellow and Orange");
		Plants testPlantUpdateSaved = this.service.updatePlant(testPlantUpdate, 1);
		
		
		Mockito.when(this.repo.findById(1)).thenReturn(testPlant);
		Mockito.when(this.repo.save(testPlantUpdate)).thenReturn(testPlantUpdateSaved); // this part is not working
		
		assertThat(testPlantUpdateSaved).isEqualTo(testPlantUpdate);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(1);
		Mockito.verify(this.repo, Mockito.times(1)).save(testPlantUpdateSaved);
		Mockito.verify(this.service, Mockito.times(1)).updatePlant(testPlantUpdate, 1);
	}
	
	@Test
	void testDelete() {
		final Integer id = 1;
		
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		
		assertThat(this.service.deletePlant(id)).isEqualTo(false);
		
		//Mockito.verify(this.repo, Mockito.times(1)).existsById(id); // no interactions with this mock
		Mockito.verify(this.service, Mockito.times(1)).deletePlant(id);
		
	}
	
	
}
