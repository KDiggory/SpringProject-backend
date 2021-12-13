package com.bae.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.backend.entity.Plants;
import com.bae.backend.exceptions.MonthNotFoundException;
import com.bae.backend.exceptions.PlantsNotFoundException;
import com.bae.backend.repo.PlantsRepo;



@Service
public class PlantsService {
	//this does the logic
	
	public PlantsRepo repo;

		
	public PlantsService(PlantsRepo repo) {
		super();
		this.repo = repo;
		
	}

	public Plants createPlant(Plants plant) {
		return this.repo.save(plant);
	}
	
	public List<Plants> getAllPlants() {
		return this.repo.findAll();
		
	}
	
	public Plants getByID(Integer id) throws PlantsNotFoundException {
		Plants saved = this.repo.findById(id).orElseThrow(() -> {
		      
		       return new PlantsNotFoundException("No plant found with that id");
		});
		return saved;
	}
	
	public List<Plants> getByPlantingMonth(String month)  throws MonthNotFoundException  { 
		List<Plants> saved = this.repo.getAllByPlantingMonth(month);
		if (saved.isEmpty()) {
		      
		       throw new MonthNotFoundException("No plant found with that planting month");
		}
		return saved;
	}
	
	// this is an extra method I had to write in order to test the custom exception
	public List<Plants> getSaved(String month) {
		List<Plants> saved = this.repo.getAllByPlantingMonth(month);
		return saved;
		
	}

	public Plants getPlantByName(String name) throws PlantsNotFoundException {
		Plants saved =  this.repo.getByName(name).orElseThrow(() ->{
			return new PlantsNotFoundException("No plant found with that name");
		});
		return saved;
	}
	
	public Plants updatePlant(Plants plant, Integer id) throws PlantsNotFoundException {
		Plants toUpdate = this.repo.findById(id).orElseThrow(() -> {
			return new PlantsNotFoundException("No plant found with that id");
		});
		toUpdate.setName(plant.getName());
		toUpdate.setFoliageColour(plant.getFoliageColour());
		toUpdate.setPlantingMonth(plant.getPlantingMonth());
		toUpdate.setPlantingPosition(plant.getPlantingPosition());
		toUpdate.setFlowerColour(plant.getFlowerColour());
		Plants update = this.repo.save(toUpdate);

		return update;
	}
		
	public boolean deletePlant(Integer id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}
	
	// will need mapToDTO if doing second table
	
		
	}