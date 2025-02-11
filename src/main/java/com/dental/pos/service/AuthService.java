package com.dental.pos.service;

public interface AuthService {
    boolean authenticate(String email, String password);
}
