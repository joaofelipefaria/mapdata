package br.com.jfelipefaria.mapdata.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the MapData API.
 * Uses Spring Boot to bootstrap and launch the REST API service.
 * The @SpringBootApplication annotation enables component scanning,
 * auto-configuration, and configuration properties support.
 */
@SpringBootApplication
public class MapDataApp {
    /**
     * Entry point for the Spring Boot application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(MapDataApp.class, args);
    }
}