package com.nkubito.ehospital.store;

import com.nkubito.ehospital.models.Role;
import com.nkubito.ehospital.models.User;
import com.nkubito.ehospital.utils.BadRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public List<User> retreiveUser() {
        List<User> userList = new ArrayList<>();
        Map<String, User> userMap = Users.INSTANCE.getUsers();
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            User user = entry.getValue();
            userList.add(user);
        }
        return userList;
    }

    public List<User> getUserByRole(String role) {
        List<User> filteredUsers = new ArrayList<>();
        Map<String, User> userMap = Users.INSTANCE.getUsers();

        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            User user = entry.getValue();

            if (user.getRole().toString().equalsIgnoreCase(role)) {
                filteredUsers.add(user);
            }
        }

        return filteredUsers;
    }
    
}
