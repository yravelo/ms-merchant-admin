package com.capitole.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capitole.entities.PriceList;

@Repository
public interface PriceListRepository extends CrudRepository<PriceList, Long> {

}
