package com.capitole.services;

import java.util.Optional;

import static org.mapstruct.factory.Mappers.getMapper;

import static com.capitole.exceptions.CapitoleExceptionCode.PRODUCT_ALREADY_EXISTS_WITH_SAME_CODE;
import static com.capitole.exceptions.CapitoleExceptionCode.UNKNOWN_PRODUCT;
import static com.capitole.validator.utils.ValidationUtils.validateEmpty;
import static com.capitole.validator.utils.ValidationUtils.validateNotEmpty;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.capitole.dto.RProduct;
import com.capitole.entities.Product;
import com.capitole.event.ProductEvent;
import com.capitole.exceptions.CapitoleException;
import com.capitole.mappers.ProductMappers;
import com.capitole.repositories.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

   private final ProductMappers mapper = getMapper(ProductMappers.class);

   private final ProductRepository productRepository;

   private final ApplicationEventPublisher eventPublisher;

   public RProduct create(RProduct rProduct) throws CapitoleException {
      Product product = mapper.fromDto(rProduct);
      validateEmpty(productRepository.findByCode(product.getCode()), PRODUCT_ALREADY_EXISTS_WITH_SAME_CODE, product.getCode());
      return mapper.toDto(productRepository.save(product));
   }

   public RProduct update(RProduct rProduct) throws CapitoleException {
      Optional<Product> oProduct = productRepository.findById(rProduct.getId());
      validateNotEmpty(oProduct, UNKNOWN_PRODUCT, rProduct.getId());
      Product product = mapper.mergeEntity(rProduct, oProduct.get());
      validateEmpty(productRepository.findByCodeAndIdIsNot(product.getCode(), product.getId()), PRODUCT_ALREADY_EXISTS_WITH_SAME_CODE,
            product.getCode());
      eventPublisher.publishEvent(new ProductEvent(this, product));
      return mapper.toDto(productRepository.save(product));
   }

   public RProduct delete(Long id) throws CapitoleException {
      Optional<Product> oProduct = productRepository.findById(id);
      validateNotEmpty(oProduct, UNKNOWN_PRODUCT, id);
      Product product = oProduct.get();
      productRepository.delete(product);
      return mapper.toDto(product);
   }

   public Page<RProduct> listAll(Pageable page) {
      return mapper.toPage(productRepository.findAll(page));
   }

   public RProduct byId(Long id) throws CapitoleException {
      Optional<Product> oProduct = productRepository.findById(id);
      validateNotEmpty(oProduct, UNKNOWN_PRODUCT, id);
      return mapper.toDto(oProduct.get());
   }
}
