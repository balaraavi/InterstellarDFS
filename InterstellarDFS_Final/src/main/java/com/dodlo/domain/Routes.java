package com.dodlo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

/**
 * @author rbalabharghav
 *
 */


@Entity
public class Routes {
	
	@javax.persistence.Id
    @GeneratedValue
    private Long id;
    private String planetOrigin;
    private String planetDestination;
    private Double distance;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlanetOrigin() {
		return planetOrigin;
	}
	public void setPlanetOrigin(String planetOrigin) {
		this.planetOrigin = planetOrigin;
	}
	public String getPlanetDestination() {
		return planetDestination;
	}
	public void setPlanetDestination(String planetDestination) {
		this.planetDestination = planetDestination;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
    
    
    

}
