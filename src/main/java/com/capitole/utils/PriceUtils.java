package com.capitole.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PriceUtils {

   public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

   public BigDecimal calculatePrice(BigDecimal price, BigDecimal discount, BigDecimal... taxes) {
      BigDecimal totalTaxes = Arrays.stream(taxes).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
      return price.subtract(applyDiscount(price, discount)).add(totalTaxes);
   }

   public BigDecimal applyDiscount(BigDecimal base, BigDecimal discount) {
      return base.multiply(discount).divide(ONE_HUNDRED);
   }
}
