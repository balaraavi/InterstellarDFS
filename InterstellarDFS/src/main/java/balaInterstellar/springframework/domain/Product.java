package balaInterstellar.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

/**
 * Created by Bala Bhargha on 4/4/19.
 */
@Entity
public class Product {

    @javax.persistence.Id
    @GeneratedValue
    private Long id;
    private String planetOrign;
    private Double distance;
    private String planetDestination;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getPlanetOrign() {
		return planetOrign;
	}

	public void setPlanetOrign(String planetOrign) {
		this.planetOrign = planetOrign;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getPlanetDestination() {
		return planetDestination;
	}

	public void setPlanetDestination(String planetDestination) {
		this.planetDestination = planetDestination;
	}

    
}
