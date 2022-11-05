package com.capitole.controller;

import static java.util.Arrays.asList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.jdbc.datasource.init.ScriptUtils.executeSqlScript;
import static org.springframework.test.util.AssertionErrors.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.sql.DataSource;

import org.javatuples.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.LinkedMultiValueMap;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles({ "test", "showconsole" })
public class PricesRestControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @ParameterizedTest
   @MethodSource({ "provider" })
   public void controllerTest(Pair<RequestBuilder, List<ResultMatcher>> args) throws Exception {
      ResultActions resultActions = mockMvc.perform(args.getValue0());
      for (ResultMatcher resultMatcher : args.getValue1()) {
         resultActions.andExpect(resultMatcher);
      }
   }

   private Stream<Arguments> provider() {
      //@formatter:off
      return Stream.of(Arguments.of(Pair.with(get("/prices/query")
                  .contentType(APPLICATION_JSON)
                  .params(new LinkedMultiValueMap<>() {{
                     add("productId", "1");
                     add("brandId", "1");
                     add("applicationDate", "2020-06-14 10:00:00");
            }}), asList(status().isOk(),
                              jsonPath("$", hasSize(1)),
                              jsonPath("$[0].productId", is(1)),
                              jsonPath("$[0].brandId", is(1)))),
                              jsonPath("$[0].price", is(35.50))),
            Arguments.of(Pair.with(get("/prices/query")
                  .contentType(APPLICATION_JSON)
                  .params(new LinkedMultiValueMap<>() {{
                     add("productId", "1");
                     add("brandId", "1");
                     add("applicationDate", "2020-06-14 16:00:00");
                  }}), asList(status().isOk(),
                              jsonPath("$", hasSize(2)),
                              jsonPath("$[0].productId", is(1)),
                              jsonPath("$[0].brandId", is(1)),
                              jsonPath("$[0].price", is(25.45)),
                              jsonPath("$[1].price", is(35.50))))
            ),
            Arguments.of(Pair.with(get("/prices/query")
                  .contentType(APPLICATION_JSON)
                  .params(new LinkedMultiValueMap<>() {{
                     add("productId", "1");
                     add("brandId", "1");
                     add("applicationDate", "2020-06-14 21:00:00");
                  }}), asList(status().isOk(),
                              jsonPath("$", hasSize(1)),
                              jsonPath("$[0].productId", is(1)),
                              jsonPath("$[0].brandId", is(1)))),
                              jsonPath("$[0].price", is(35.50))
            ),
            Arguments.of(Pair.with(get("/prices/query")
                  .contentType(APPLICATION_JSON)
                  .params(new LinkedMultiValueMap<>() {{
                     add("productId", "1");
                     add("brandId", "1");
                     add("applicationDate", "2020-06-15 10:00:00");
                  }}), asList(status().isOk(),
                              jsonPath("$", hasSize(2)),
                              jsonPath("$[0].productId", is(1)),
                              jsonPath("$[0].brandId", is(1)))),
                              jsonPath("$[0].price", is(30.50)),
                              jsonPath("$[0].price", is(35.50))
            ),
            Arguments.of(Pair.with(get("/prices/query")
                  .contentType(APPLICATION_JSON)
                  .params(new LinkedMultiValueMap<>() {{
                     add("productId", "1");
                     add("brandId", "1");
                     add("applicationDate", "2020-06-16 21:00:00");
                  }}), asList(status().isOk(),
                              jsonPath("$", hasSize(2)),
                              jsonPath("$[0].productId", is(1)),
                              jsonPath("$[0].brandId", is(1)))),
                              jsonPath("$[0].price", is(38.95)),
                              jsonPath("$[0].price", is(35.50))
            )
      );
      //@formatter:on
   }

   @BeforeAll
   public void before(@Autowired DataSource dataSource, @Autowired PlatformTransactionManager transactionManager) {
      new TransactionTemplate(transactionManager).execute((ts) -> {
         try (Connection conn = dataSource.getConnection()) {
            executeSqlScript(conn, new ClassPathResource("/base-data/script.sql"));
            conn.commit();
         } catch (SQLException e) {
            fail(e.getMessage());
         }
         return null;
      });
   }
}
