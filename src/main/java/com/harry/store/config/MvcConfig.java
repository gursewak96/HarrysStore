package com.harry.store.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${storage.image.product.path}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path path = Paths.get(uploadDir);
        String productImageStoragePath = path.toFile().getAbsolutePath();
        System.out.println(productImageStoragePath);
        registry.addResourceHandler("/products/images/**")
                .addResourceLocations("file:/"+productImageStoragePath+"/");
    }
}
