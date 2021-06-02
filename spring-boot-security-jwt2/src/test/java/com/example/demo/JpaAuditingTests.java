package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JpaAuditingTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testJpaAuditing() {
        User user = new User();
        user.setUsername("test jpa auditing");
        user.setPassword("PWD");
        user.setEnabled(true);
        userRepository.save(user);

       assertNotNull(user.getId());
       assertNotNull(user.getCreatedAt());
       assertNotNull(user.getUpdatedAt());
    }
}
