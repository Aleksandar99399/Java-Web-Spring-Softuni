package com.softuni.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestTemplateApplicationInit implements CommandLineRunner {

    @Autowired
    RestTemplate template;

    @Override
    public void run(String... args) throws Exception {
        ResponseEntity<List<Authors>> authorsReponse =template.exchange(
                "http://localhost:8080/authors",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Authors>>() {
                }
        );

        if (authorsReponse.hasBody()) {
            authorsReponse.getBody().forEach(System.out::println);
        }


    }
}
