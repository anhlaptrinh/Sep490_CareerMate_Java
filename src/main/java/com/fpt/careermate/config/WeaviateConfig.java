package com.fpt.careermate.config;

import io.weaviate.client.Config;
import io.weaviate.client.WeaviateClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeaviateConfig {

    @Bean
    public WeaviateClient weaviateClient() {
        Config config = new Config("http", "localhost:8081");
        return new WeaviateClient(config);
    }
}
