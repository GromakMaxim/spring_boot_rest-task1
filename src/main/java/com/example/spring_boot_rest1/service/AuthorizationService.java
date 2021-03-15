package com.example.spring_boot_rest1.service;

import com.example.spring_boot_rest1.exceptions.InvalidCredentials;
import com.example.spring_boot_rest1.repository.UserRepository;

public class AuthorizationService {
    private UserRepository repository;

    public AuthorizationService(UserRepository repository) {
        this.repository = repository;
    }

    public String check(String user, String password) {

        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }

        return repository.getByIdentifier(user + password).toString();
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}