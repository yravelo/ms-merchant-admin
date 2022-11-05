package com.capitole.utils;

import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.stream;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.ReflectionTestUtils.invokeMethod;
import static org.springframework.util.ReflectionUtils.getAllDeclaredMethods;
import static org.springframework.util.ReflectionUtils.makeAccessible;

import org.javatuples.Triplet;

public abstract class AbstractTestClassUtils {

   public static final Map<String, Triplet<Class[], Object[], Consumer<Object>>> TEST_CASE = new HashMap<>();

   protected void processAction(String methodName) throws NoSuchMethodException {
      Class utilsClass = whatClassWillBeProcessed();
      List<Triplet<Class[], Object[], Consumer<Object>>> testCaseList = TEST_CASE
            .entrySet()
            .stream()
            .filter(map -> map.getKey().contains(methodName))
            .map(map -> map.getValue())
            .collect(Collectors.toList());

      for (Triplet triplet : testCaseList) {
         Method method = utilsClass.getDeclaredMethod(methodName,
               copyOfRange((Class[]) triplet.getValue0(), 0, ((Object[]) triplet.getValue0()).length));
         makeAccessible(method);

         assertTrue(stream(getAllDeclaredMethods(utilsClass)).filter(m -> methodName.equals(m.getName())).findFirst().isPresent());
         assertFalse(testCaseList.isEmpty());

         Object obj = invokeMethod(utilsClass, methodName, copyOfRange((Object[]) triplet.getValue1(), 0, ((Object[]) triplet.getValue1()).length));
         if (Objects.nonNull(obj)) {
            ((Consumer) triplet.getValue2()).accept(obj);
         } else {
            ((Consumer) triplet.getValue2()).accept(((Object[]) triplet.getValue1())[0]);
         }
      }
   }

   protected abstract Class whatClassWillBeProcessed();

}
