package com.ltw.graphqlshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Redirect root to index page
        registry.addRedirectViewController("/", "/index.html");
        registry.addRedirectViewController("/login", "/login.html");
        registry.addRedirectViewController("/user-home", "/user-home.html");
        registry.addRedirectViewController("/admin", "/admin.html");
    }
}
