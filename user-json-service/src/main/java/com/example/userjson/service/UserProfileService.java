package com.example.userjson.service;

import com.example.userjson.model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserProfileService {

    private Map<Long, UserProfile> users = new HashMap<>();
    private Long currentId = 1L;

    public UserProfile createUser(UserProfile user) {
        user.setId(currentId++);
        users.put(user.getId(), user);
        return user;
    }

    public UserProfile getUser(Long id) {
        return users.get(id);
    }

    public UserProfile updateUser(Long id, UserProfile user) {
        user.setId(id);
        users.put(id, user);
        return user;
    }

    public void deleteUser(Long id) {
        users.remove(id);
    }

    public Collection<UserProfile> getAllUsers() {
        return users.values();
    }
}