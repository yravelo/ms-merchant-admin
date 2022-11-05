package com.capitole.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capitole.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
