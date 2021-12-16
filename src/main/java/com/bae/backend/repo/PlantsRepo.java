package com.bae.backend.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bae.backend.entity.Plants;

public interface PlantsRepo extends JpaRepository<Plants, Integer>{
	
	Optional<Plants> getByName(String type);
	
	List<Plants> getAllByFoliageColour(String colour);
	
	List<Plants> getAllByPlantingMonth(String season);
	
	List<Plants> getAllByPlantingPosition(String position);

}