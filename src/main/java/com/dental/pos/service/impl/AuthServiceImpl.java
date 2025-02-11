package com.dental.pos.service.impl;

import com.dental.pos.entity.Admin;
import com.dental.pos.repository.AdminRepository;
import com.dental.pos.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AdminRepository adminRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean authenticate(String email, String password) {
        Optional<Admin> admin = adminRepository.findByEmail(email);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        System.out.println("Encoded Password: " + encodedPassword);

        return admin.isPresent() && passwordEncoder.matches(password, admin.get().getPassword());
    }
}
