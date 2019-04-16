package com.dodlo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dodlo.domain.Planets;

/**
 * @author rbalabharghav
 *
 */

@Repository
public interface PlanetRepository extends CrudRepository<Planets, Long> {

	

}
