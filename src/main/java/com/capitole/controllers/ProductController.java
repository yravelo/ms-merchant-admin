package com.capitole.controllers;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capitole.dto.RProduct;
import com.capitole.exceptions.CapitoleException;
import com.capitole.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Validated
@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

   private final ProductService service;

   @PostMapping
   public RProduct create(@RequestBody @Valid RProduct product) throws CapitoleException {
      return service.create(product);
   }

   @PutMapping
   public RProduct update(@RequestBody @Valid RProduct product) throws CapitoleException {
      return service.update(product);
   }

   @DeleteMapping(path = "{id}")
   public RProduct deleteBranch(@PathVariable(value = "id") Long id) throws CapitoleException {
      return service.delete(id);
   }

   @GetMapping
   public Page<RProduct> listAll(Pageable page) {
      return service.listAll(page);
   }

   @GetMapping(path = "{id}")
   public RProduct byId(@PathVariable(value = "id") Long id) throws CapitoleException {
      return service.byId(id);
   }

}
