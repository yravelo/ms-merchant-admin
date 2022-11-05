package com.capitole.utils;

import java.math.BigDecimal;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import org.javatuples.Triplet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.util.AssertionErrors;

@TestInstance(PER_CLASS)
public class PriceUtilsTest extends AbstractTestClassUtils {

   @ParameterizedTest
   @CsvSource({ "applyDiscount", "calculatePrice" })
   public void masterScheduleUtils(final String methodName) {
      try {
         processAction(methodName);
      } catch (NoSuchMethodException e) {
         AssertionErrors.fail(e.getMessage());
      }
   }

   @Override
   protected Class whatClassWillBeProcessed() {
      return PriceUtils.class;
   }

   @BeforeAll
   public void init() {

      TEST_CASE.put("applyDiscountCase1",
            Triplet.with(new Class[] { BigDecimal.class, BigDecimal.class }, new Object[] { BigDecimal.valueOf(75.35), BigDecimal.valueOf(3.2) },
                  new Consumer<Object>() {

                     @Override
                     public void accept(Object obj) {
                        assertEquals(obj, BigDecimal.valueOf(2.4112));
                     }
                  }));
      TEST_CASE.put("calculatePriceCase1", Triplet.with(new Class[] { BigDecimal.class, BigDecimal.class, BigDecimal[].class },
            new Object[] { BigDecimal.valueOf(100), BigDecimal.valueOf(10), new BigDecimal[] { BigDecimal.valueOf(2.5) } }, new Consumer<Object>() {

               @Override
               public void accept(Object obj) {
                  assertEquals(obj, BigDecimal.valueOf(92.5));
               }
            }));

   }

}

