package com.dronefeeder.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

// swagger configuration by larissa falcão at online class;
// url: http://localhost:8080/swagger-ui/index.html
/**
 * SwaggerConfiguration class.
 */
@Configuration
public class SwaggerConfiguration {
  /**
   * Docket.
   */
  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
  }
}