package com.capitole.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.capitole.entities.Product;
import com.capitole.services.PricesService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@AllArgsConstructor
public class ProductEventListener {

   private final PricesService service;

   @Async
   @EventListener(classes = ProductEvent.class)
   public void handle(ProductEvent event) {
      Product product = event.getProduct();
      log.info("Update prices for product {}", product.getId());
      service.updatePrices(product);
   }
}
