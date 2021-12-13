package com.bae.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plants {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String foliageColour;
	private String plantingMonth;
	private String plantingPosition;
	private String flowerColour;
	
	
	public Plants(Integer id, String name, String foliageColour, String plantingMonth, String plantingPosition,
			String flowerColour) {
		super();
		this.id = id;
		this.name = name;
		this.foliageColour = foliageColour;
		this.plantingMonth = plantingMonth;
		this.plantingPosition = plantingPosition;
		this.flowerColour = flowerColour;
	}


	public Plants() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFoliageColour() {
		return foliageColour;
	}


	public void setFoliageColour(String foliageColour) {
		this.foliageColour = foliageColour;
	}


	public String getPlantingMonth() {
		return plantingMonth;
	}


	public void setPlantingMonth(String plantingMonth) {
		this.plantingMonth = plantingMonth;
	}


	public String getPlantingPosition() {
		return plantingPosition;
	}


	public void setPlantingPosition(String plantingPosition) {
		this.plantingPosition = plantingPosition;
	}


	public String getFlowerColour() {
		return flowerColour;
	}


	public void setFlowerColour(String flowerColour) {
		this.flowerColour = flowerColour;
	}


	@Override
	public String toString() {
		return "Plants [id=" + id + ", name=" + name + ", foliageColour=" + foliageColour + ", plantingMonth="
				+ plantingMonth + ", plantingPosition=" + plantingPosition + ", flowerColour=" + flowerColour + "]";
	}
	


}
