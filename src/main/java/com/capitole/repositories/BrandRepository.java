package com.capitole.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capitole.entities.Brand;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {

}
