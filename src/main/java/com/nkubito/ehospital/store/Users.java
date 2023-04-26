package com.nkubito.ehospital.store;

import com.nkubito.ehospital.models.User;
import com.nkubito.ehospital.utils.BadRequest;

import java.util.HashMap;
import java.util.Map;

public enum Users {
    INSTANCE;
    final private Map<String, User> users;

    Users() {
        users = new HashMap<>();
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        if (contains(user)) {
            throw new BadRequest("User already exists");
        }

        users.put(getKey(user), user);
    }

    public String getKey(User user) {
        return switch (user.getRole()) {
            case Physician -> user.getEmail();
            case Pharmacist -> user.getPhoneNumber();
            case Patient -> user.getUsername();
        };
    }

    private boolean contains(User user) {
        return switch (user.getRole()) {
            case Physician -> users.keySet().stream().anyMatch(u -> u.equals(user.getEmail()));
            case Pharmacist -> users.keySet().stream().anyMatch(u -> u.equals(user.getPhoneNumber()));
            case Patient -> users.keySet().stream().anyMatch(u -> u.equals(user.getUsername()));
        };
    }
}
