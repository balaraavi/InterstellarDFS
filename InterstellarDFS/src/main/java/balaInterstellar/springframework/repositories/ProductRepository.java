package balaInterstellar.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import balaInterstellar.springframework.domain.Product;

/**
 * Created by Bala Bhargha on 4/4/19.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
}
