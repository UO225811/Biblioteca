package com.capgemini.rest.service;

import org.springframework.stereotype.Service;

@Service
public class RolesRestService {
    String[] roles = {"ROLE_LECTOR", "ROLE_ADMIN"};

    public String[] getRoles() {
        return roles;
    }
}
