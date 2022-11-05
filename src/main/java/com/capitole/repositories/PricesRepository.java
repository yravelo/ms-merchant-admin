package com.capitole.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capitole.dto.RPricesReduce;
import com.capitole.entities.Prices;

@Repository
public interface PricesRepository extends PagingAndSortingRepository<Prices, Long> {

   //@formatter:off
   @Query(value = "select new com.capitole.dto.RPricesReduce("
         + "p.product.id, "
         + "p.brand.id, "
         + "p.priceList.id, "
         + "p.startDate, "
         + "p.endDate, "
         + "p.price ) "
         + "from Prices p "
         + "where  p.brand.id = :brandId and p.product.id = :productId and  :applicationDate between p.startDate and p.endDate "
         + "order by p.priority desc ")
   List<RPricesReduce> obtainPricesByProductIdBrandIdAndApplicationDate(
         @Param("applicationDate") LocalDateTime applicationDate,
         @Param("productId") Long productId,
         @Param("brandId") Long brandId);
   //@formatter:on
}
