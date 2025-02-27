package com.dental.pos.exception;

public class ClinicServiceNotFoundException extends RuntimeException {
    public ClinicServiceNotFoundException(String message) {
        super(message);
    }
}
