package com.adrianLopez.proyectoPokemon.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {
    private static String url;

    public static String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        ApplicationProperties.url = url;
    }
}
