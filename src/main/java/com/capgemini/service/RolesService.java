package com.capgemini.service;

import org.springframework.stereotype.Service;

@Service
public class RolesService {
    String[] roles = {"LECTOR", "ADMIN"};

    public String[] getRoles() {
        return roles;
    }
}
