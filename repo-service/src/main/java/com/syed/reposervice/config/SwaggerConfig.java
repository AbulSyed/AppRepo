package com.syed.reposervice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    // provides metadata about API such as its title, description, version, terms of service, and contact information.
    // metadata is displayed in the Swagger UI documentation
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Repo service API",
                "Repo service API documentation",
                "1",
                "Terms of services",
                new Contact("Abul", "", "syed1234@hotmail.co.uk"),
                "License of API",
                "API license URL",
                Collections.emptyList()
        );
    }

    // Docket class is used to customize the Swagger API documentation generation, such as the API endpoints to include,
    // the API info (metadata), and the Swagger UI interface settings
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
