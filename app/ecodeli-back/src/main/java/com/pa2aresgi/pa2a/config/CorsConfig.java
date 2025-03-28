package com.pa2aresgi.pa2a.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Autoriser les requêtes depuis toutes les origines (pour le dev, à restreindre en prod)
        config.setAllowedOrigins(List.of("http://localhost:3000/")); // Remplace par l'URL de ton front

        // Autoriser ces méthodes HTTP
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Autoriser ces en-têtes
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        // Autoriser les cookies et les credentials (si nécessaire)
        config.setAllowCredentials(true);

        // Appliquer la config aux routes
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
