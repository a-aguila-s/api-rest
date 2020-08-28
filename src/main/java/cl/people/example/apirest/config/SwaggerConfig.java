package cl.people.example.apirest.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Alex Águila date 27-08-2020
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(
                        RequestHandlerSelectors
                            .withClassAnnotation(RestController.class))
                            .paths(PathSelectors
                            .any())
                            .build().apiInfo(this.apiInfo());
    }

    private ApiInfo apiInfo() {
        //TODO info to complete API info
        return new ApiInfo(
            "REST API FOR 23PEOPLE TEST",
            "Sringboot + MySQL + OAuth2 + JWT",
            "0.0.1-SNAPSHOT",
            "Copyrigths to Alex Águila",
            new Contact("Alex Águila Salinas", "https://google.com", "ac.aguila.s92@gmail.com"),
            "API license",
            "http://www.google.com",
            Collections.emptyList());
    }
    
}