package com.capitole.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitole.dto.RPricesReduce;
import com.capitole.model.CapitoleLocalDateTime;
import com.capitole.services.PricesService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/prices")
public class PricesRestController {

   private final PricesService service;

   @GetMapping(path = "/query")
   public List<RPricesReduce> queryList(@RequestParam @CapitoleLocalDateTime LocalDateTime applicationDate, @RequestParam Long productId,
         @RequestParam Long brandId) {
      return service.obtainPricesByProductIdBrandIdAndApplicationDate(applicationDate, productId, brandId);
   }

}
