package com.capitole.entities;

import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "product", indexes = { @Index(name = "product_code_ux", columnList = "code") })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product extends Auditable {

   @Id
   @EqualsAndHashCode.Include
   @GeneratedValue(strategy = IDENTITY)
   private Long id;

   @NotNull
   @Size(max = 50)
   @EqualsAndHashCode.Include
   private String code;

   @NotNull
   @Size(max = 100)
   private String name;

   @Size(max = 500)
   private String description;

   @NotNull
   @Digits(integer = 12, fraction = 2)
   private BigDecimal standardPrice;

}
