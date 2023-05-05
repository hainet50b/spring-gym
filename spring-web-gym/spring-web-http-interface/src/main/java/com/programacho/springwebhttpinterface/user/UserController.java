package com.programacho.springwebhttpinterface.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final List<User> users;

    public UserController() {
        users = new ArrayList<>();
        users.add(new User("1", "hainet50b"));
        users.add(new User("2", "programacho.com"));
    }

    @GetMapping("/")
    public List<User> findAll() {
        return users;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return users.stream().filter(it -> it.id().equals(id)).findFirst().orElse(null);
    }

    @PostMapping("/")
    public User save(@RequestBody User user) {
        users.add(user);

        return user;
    }
}
