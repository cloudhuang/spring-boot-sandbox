package com.example.dataloader;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setFirstName("First Name: " + i);
            user.setLastName("Last Name: " + i);
            user.setSalary(i);
            userRepository.save(user);
        }
    }
}
