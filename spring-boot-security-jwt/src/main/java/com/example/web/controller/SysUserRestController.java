package com.example.web.controller;

import com.example.entity.SysUser;
import com.example.exception.ResourceNotFoundException;
import com.example.respository.SysUserRepository;
import com.example.web.payload.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class SysUserRestController {
    @Autowired
    private SysUserRepository userRepository;

    @GetMapping("/{username}")
    public UserProfile getUserProfile(@PathVariable String username) {
        SysUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(""));

        return new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());
    }
}
