package com.capitole.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capitole.dto.RPricesReduce;
import com.capitole.repositories.PricesRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PricesService {

   private final PricesRepository pricesRepository;

   public List<RPricesReduce> obtainPricesByProductIdBrandIdAndApplicationDate(LocalDateTime applicationDate, Long productId, Long brandId) {
      return pricesRepository.obtainPricesByProductIdBrandIdAndApplicationDate(applicationDate, productId, brandId);
   }

}
