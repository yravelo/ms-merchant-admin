package com.capitole.dto;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RProduct implements Serializable {

   private Long id;

   @Size(max = 50)
   private String code;

   @NotNull
   @Size(max = 100)
   private String name;

   @Size(max = 500)
   private String description;

}
