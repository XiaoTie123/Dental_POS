package com.dental.pos.config;

import com.dental.pos.util.common.TextConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TextConversionConfig {

    @Bean
    public TextConverter textConverter() {
        return new TextConverter(); // Assume TextConverter is a class that handles text conversions
    }
}
