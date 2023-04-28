package com.nkubito.ehospital.services;

import com.nkubito.ehospital.interfaces.UserService;
import com.nkubito.ehospital.models.Role;
import com.nkubito.ehospital.utils.BadRequest;

public enum UserServiceFactory {
    INSTANCE;

    public UserService getUserService(Role role) {
        return switch (role) {
            case Pharmacist -> PharmacistService.INSTANCE;
            case Patient -> PatientService.INSTANCE;
            case Physician -> PhysicianService.INSTANCE;
        };


    }

    public UserService getUserService(Object role) {
        try {
            return getUserService(Role.valueOf((String) role));
        } catch (Exception e) {
            throw new BadRequest("Role not found");
        }

    }
}
