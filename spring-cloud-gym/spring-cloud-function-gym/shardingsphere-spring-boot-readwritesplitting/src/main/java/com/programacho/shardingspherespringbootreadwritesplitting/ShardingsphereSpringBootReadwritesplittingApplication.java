package com.programacho.shardingspherespringbootreadwritesplitting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Random;

@SpringBootApplication
@RestController
@RequestMapping("/emp")
public class ShardingsphereSpringBootReadwritesplittingApplication {

    private final JdbcTemplate jdbcTemplate;

    public ShardingsphereSpringBootReadwritesplittingApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(ShardingsphereSpringBootReadwritesplittingApplication.class, args);
    }

    @GetMapping
    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("SELECT * FROM emp");
    }

    @PostMapping
    public void insert() {
        jdbcTemplate.update(
                "INSERT INTO emp (id, name) VALUES(?, UUID())",
                new Random().nextInt(100)
        );
    }
}
