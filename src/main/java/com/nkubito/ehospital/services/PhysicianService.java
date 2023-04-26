package com.nkubito.ehospital.services;

import com.nkubito.ehospital.interfaces.UserService;
import com.nkubito.ehospital.utils.BadRequest;

public enum PhysicianService implements UserService {
    INSTANCE;

    @Override
    public String getMainKey() {
        return "email";
    }

    public void validatePassword(String password) {
        if (password.length() < 7 || password.length() > 8) {
            throw new BadRequest("password length should be between 4 and 6");
        }
    }
}
