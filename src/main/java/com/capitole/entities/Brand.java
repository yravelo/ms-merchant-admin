package com.capitole.entities;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "brand", indexes = { @Index(name = "brand_name_ux", columnList = "name") })
public class Brand {

   @Id
   @GeneratedValue(strategy = IDENTITY)
   @EqualsAndHashCode.Include
   private Long id;

   @NotNull
   @Size(max = 100)
   @EqualsAndHashCode.Include
   private String name;

   @NotNull
   protected LocalDateTime createDateTime;

   protected LocalDateTime updateDateTime;

}
