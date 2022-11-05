package com.capitole;

import static org.springframework.boot.WebApplicationType.SERVLET;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MerchantAdminApplication {

   public static void main(String[] args) {
      new SpringApplicationBuilder(MerchantAdminApplication.class).web(SERVLET).run(args);
   }

}
