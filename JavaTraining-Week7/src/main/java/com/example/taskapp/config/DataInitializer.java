package com.example.taskapp.config;

import com.example.taskapp.entity.UserAccount;
import com.example.taskapp.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UserAccountRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (!repo.existsByUsername("testuser")) {
                repo.save(new UserAccount(
                        "testuser",
                        encoder.encode("password"),
                        "USER"
                ));
            }
        };
    }
}