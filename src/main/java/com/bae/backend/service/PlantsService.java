package com.bae.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.backend.entity.Plants;
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
	public List<Plants> getByPlantingMonth(String month)  { //throws MonthNotFoundException
		return this.repo.getAllByPlantingMonth(month);
	}
//	.orElseThrow(() -> {
//		      
//		       return new MonthNotFoundException("No plant found with that planting month");
//		});
//		return saved;
//	}
	public List<Plants> getByPlantingPosition(String position)  { //throws PlantingPositionNotFoundException
		return this.repo.getAllByPlantingPosition(position);
	}
//				.orElseThrow(() -> {
//		      
//		       return new PlantingPositionNotFoundException("No plant found with that planting position");
//		});
//		return saved;
//	}
//	
	public Plants getPlantByName(String name) {
		return this.repo.getByName(name);
	}
	public Plants updatePlant(Plants plant, Integer id) {
		Plants toUpdate = this.repo.findById(id).get();
		toUpdate.setName(plant.getName());
		toUpdate.setFoliageColour(plant.getFoliageColour());
		toUpdate.setPlantingMonth(plant.getPlantingMonth());
		toUpdate.setPlantingPosition(plant.getPlantingPosition());
		toUpdate.setFlowerColour(plant.getFlowerColour());
		return this.repo.save(toUpdate);
		
	}
	public Plants updatePlantByName(String name, Plants newPlant) {
		Plants toUpdate = this.repo.getByName(name);
		toUpdate.setFoliageColour(toUpdate.getFoliageColour());
		toUpdate.setPlantingMonth(toUpdate.getPlantingMonth());
		toUpdate.setPlantingPosition(toUpdate.getPlantingPosition());
		toUpdate.setFlowerColour(toUpdate.getFlowerColour());
		return this.repo.save(toUpdate);
		
	}
	public boolean deletePlant(Integer id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}
	
	// will need mapToDTO if doing second table
	
		
	}