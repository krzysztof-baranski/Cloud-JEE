package com.example.ignite.app.user;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserList {

    private static final User[] USERS = new User[]{new User("user", "user"), new User("admin", "admin")};

    private final Set<User> users;

    public UserList() {
        users = Arrays.asList(USERS)
                .stream()
                .collect(Collectors.toSet());
    }

    public Optional<User> findOne(String login) {
        return users.stream()
                .filter(user -> user.getUsername().equals(login))
                .findFirst();
    }

}
