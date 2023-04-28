package com.nkubito.ehospital.services;

import com.nkubito.ehospital.interfaces.UserService;
import com.nkubito.ehospital.utils.BadRequest;

public enum PharmacistService implements UserService {
    INSTANCE;

    @Override
    public String getMainKey() {
        return "phoneNumber";
    }

    @Override
    public void validatePassword(String password) {
        if (password.length() < 9 || password.length() > 10) {
            throw new BadRequest("password length should be between 4 and 6");
        }
    }
}
