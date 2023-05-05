package com.programacho.springwebhttpinterface.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("/users")
public interface UserClient {

    @GetExchange("/")
    List<User> findAll();

    @GetExchange("/{id}")
    User findById(@PathVariable String id);

    @PostExchange("/")
    User save(@RequestBody User user);
}
