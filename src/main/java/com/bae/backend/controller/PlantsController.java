package com.bae.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.backend.entity.Plants;

import antlr.collections.List;

@CrossOrigin(origins = "*")
@RestController
public class PlantsController {
	
	private PlantsService service;

	@Autowired 
	public PlantsController(PlantsService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createPlant")
	public ResponseEntity<Plants> createPlant(@RequestBody Plants plant) {
		Plants created = this.service.createPlant(plant);
		ResponseEntity<Plants> response = new ResponseEntity<Plants>(plant, HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("getAll")
	public ResponseEntity<List<Plants>> getAllPlants(){
		return ResponseEntity.ok(this.service.getAllPlants());
	}
	
	@GetMapping("/getPlantById/{id}")
	public ResponseEntity<Plants> getPlantById(@PathVariable Integer id) throws PlantsNotFoundException {
		return ResponseEntity.ok(this.service.getByID(id));
	}
	
	@GetMapping("/getPlantByName/{name}")
	public ResponseEntity<Plants> getPlantByName(@PathVariable String name) {
		return ResponseEntity.ok(this.service.getPlantByName(name));
	}
	@GetMapping("/getPlantByMonth/{month}")
	public ResponseEntity<List<Plants>> getPlantByMonth(@PathVariable String month) throws MonthNotFoundException {
		return ResponseEntity.ok(this.service.getByPlantingMonth(month));
	}
	
	
	@PutMapping("/updatePlant/{id}") 
	public ResponseEntity<Plants> updatePlant(@PathVariable Integer id, @RequestBody Plants newPlant) {
		Plants body = this.service.updatePlant(newPlant, id);
		ResponseEntity<Plants> response = new ResponseEntity<Plants>(body, HttpStatus.ACCEPTED);
		return response;
	}
}
