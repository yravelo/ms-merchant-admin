package com.capitole.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.capitole.entities.Prices;

@Repository
public interface PricesRepository extends PagingAndSortingRepository<Prices, Long> {

}
