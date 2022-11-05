package com.capitole.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.capitole.entities.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

   Optional<Product> findByCode(String code);

   Optional<Product> findByCodeAndIdIsNot(String code, Long id);
}
