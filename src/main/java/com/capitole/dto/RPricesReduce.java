package com.capitole.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.capitole.model.CapitoleLocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RPricesReduce implements Serializable {

   private Long productId;

   private Long brandId;

   private Long priceListId;

   @CapitoleLocalDateTime
   private LocalDateTime startDate;

   @CapitoleLocalDateTime
   private LocalDateTime endDate;

   private BigDecimal price;

}
