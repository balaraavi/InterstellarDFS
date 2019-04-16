package com.dodlo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dodlo.domain.Routes;

/**
 * @author rbalabharghav
 *
 */

@Repository
public interface RoutesRepository extends CrudRepository<Routes, Long> {

}
