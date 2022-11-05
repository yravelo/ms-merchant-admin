package com.capitole.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import com.capitole.dto.RProduct;
import com.capitole.entities.Product;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMappers {

   Product fromDto(RProduct dto);

   RProduct toDto(Product entity);

   @Mapping(target = "id", ignore = true)
   Product mergeEntity(RProduct dto, @MappingTarget Product entity);

   default Page<RProduct> toPage(Page<Product> page) {
      return page.map(this::toDto);
   }

}
