package com.capitole;

import static org.springframework.boot.WebApplicationType.SERVLET;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MerchantAdminApplication {

   public static void main(String[] args) {
      new SpringApplicationBuilder(MerchantAdminApplication.class).web(SERVLET).run(args);
   }

}
