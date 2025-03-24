package com.alisson.gerenciamento_de_tarefas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Collections;

@Configuration
public class FavIconConfig {
    private final static String FAVICON_ICO = "img/favicon.ico"; // URL que será acessada

    @Bean
    public SimpleUrlHandlerMapping customFavIcon() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Integer.MIN_VALUE);
        mapping.setUrlMap(Collections.singletonMap(FAVICON_ICO, favIconRequest())); // Mapeia a URL correta
        return mapping;
    }

    protected ResourceHttpRequestHandler favIconRequest() {
        ResourceHttpRequestHandler handler = new ResourceHttpRequestHandler();
        handler.setLocations(Collections.singletonList(new ClassPathResource("static/"))); // Diretório correto
        return handler;
    }
}
