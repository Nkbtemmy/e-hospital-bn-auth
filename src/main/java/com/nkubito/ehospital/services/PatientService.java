package com.nkubito.ehospital.services;

import com.nkubito.ehospital.interfaces.UserService;
import com.nkubito.ehospital.utils.BadRequest;

public enum PatientService implements UserService {
    INSTANCE;

    public void validatePassword(String password) {
        if (password.length() < 4 || password.length() > 6) {
            throw new BadRequest("password length should be between 4 and 6");
        }
    }

    @Override
    public String getMainKey() {
        return "username";
    }
}
