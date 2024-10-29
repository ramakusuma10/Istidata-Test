package com.example.kendaraan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfigKendaraan implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Izinkan semua endpoint
                .allowedOrigins("http://localhost:63342") // Ganti dengan origin yang sesuai
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Metode HTTP yang diizinkan
                .allowedHeaders("*") // Izinkan semua header
                .allowCredentials(true); // Izinkan pengiriman cookie
    }
}