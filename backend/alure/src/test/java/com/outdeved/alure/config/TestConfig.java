package com.outdeved.alure.config;

import java.net.http.HttpClient;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }

}
