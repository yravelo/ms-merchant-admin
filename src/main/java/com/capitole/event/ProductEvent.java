package com.capitole.event;

import org.springframework.context.ApplicationEvent;

import com.capitole.entities.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductEvent extends ApplicationEvent {

   private Product product;

   public ProductEvent(Object source, Product product) {
      super(source);
      this.product = product;
   }

}
