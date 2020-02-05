package com.example.service;

import com.example.entity.User;
import com.example.web.payload.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User save(UserDto user);
    Page<User> findAll(Pageable pageable);
    void delete(Long id);
    User findOne(String username);
    User findById(Long id);
    UserDto update(UserDto userDto);
}