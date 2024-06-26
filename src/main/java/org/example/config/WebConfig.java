package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/templates/static/css/");
        registry.addResourceHandler("/static/js/**").addResourceLocations("classpath:/templates/static/js/");
        registry.addResourceHandler("/static/images/**").addResourceLocations("classpath:/templates/static/images/");
    }

}
