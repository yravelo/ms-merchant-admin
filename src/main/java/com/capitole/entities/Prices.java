package com.capitole.entities;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prices")
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Prices extends Auditable {

   @Id
   @GeneratedValue(strategy = IDENTITY)
   @EqualsAndHashCode.Include
   private Long id;

   @NotNull
   @ManyToOne
   private Brand brand;

   @NotNull
   @ManyToOne
   private Product product;

   @NotNull
   @ManyToOne
   private PriceList priceList;

   @Builder.Default
   private Integer priority = 0;

   @NotNull
   private LocalDateTime startDate;

   @NotNull
   private LocalDateTime endDate;

   @NotNull
   @Digits(integer = 12, fraction = 2)
   private BigDecimal price;

   @NotNull
   @Digits(integer = 3, fraction = 0)
   private Integer currencyIsoCode;

   @NotNull
   @Digits(integer = 2, fraction = 2)
   private BigDecimal discount;

   @NotNull
   @Builder.Default
   @Digits(integer = 2, fraction = 2)
   private BigDecimal tax = ZERO;

}
