package com.dodlo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

/**
 * @author rbalabharghav
 *
 */
@Entity
public class Planets {

	@javax.persistence.Id
    @GeneratedValue
    private Long id;
    private String planetNode;
    private String planetName;
	public String getPlanetNode() {
		return planetNode;
	}
	public void setPlanetNode(String planetNode) {
		this.planetNode = planetNode;
	}
	public String getPlanetName() {
		return planetName;
	}
	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
