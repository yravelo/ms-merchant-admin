package com.capitole.services;

import static java.util.Objects.nonNull;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capitole.dto.RPricesReduce;
import com.capitole.entities.Product;
import com.capitole.repositories.PricesRepository;
import com.capitole.utils.PriceUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PricesService {

   private final PricesRepository pricesRepository;

   public List<RPricesReduce> obtainPricesByProductIdBrandIdAndApplicationDate(LocalDateTime applicationDate, Long productId, Long brandId) {
      return pricesRepository.obtainPricesByProductIdBrandIdAndApplicationDate(applicationDate, productId, brandId);
   }

   public void updatePrices(Product product) {
      if (nonNull(product)) {
         pricesRepository.saveAll(pricesRepository
               .findByProduct(product)
               .stream()
               .map(ps -> ps.toBuilder().price(PriceUtils.calculatePrice(ps.getProduct().getStandardPrice(), ps.getDiscount(), ps.getTax())).build())
               .toList());
      }
   }

}
