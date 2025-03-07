package com.dental.pos.config;

import com.dental.pos.interceptor.InterceptorConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    InterceptorConfig interceptorConfig;

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp"); // Set JSP location
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorConfig)
                .addPathPatterns("/dashboard/**", "/bill/**", "/patients/**", "/appointment/**")
                .excludePathPatterns("/", "/login", "/logout", "/static/**");
    }
}
