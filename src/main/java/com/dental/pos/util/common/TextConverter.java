package com.dental.pos.util.common;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class TextConverter {
    public String convert(String input) {
        // Example conversion logic
        return new String(input.getBytes(), StandardCharsets.UTF_8);
    }
}
