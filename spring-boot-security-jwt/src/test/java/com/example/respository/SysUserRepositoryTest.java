package com.example.respository;

import com.example.entity.RoleName;
import com.example.entity.SysRole;
import com.example.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SysUserRepositoryTest {
    @Autowired
    private SysUserRepository userRepository;

    @Autowired
    private SysRoleRepository roleRepository;

    @Test
    public void should_save_sys_user_success() throws Exception {
        SysUser user = new SysUser();
        user.setName("Liping Huang");
        user.setEmail("liping.huang@live.com");
        user.setPassword("123456");
        Set<SysRole> roles = new HashSet<>();

        roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userRepository.save(user);

        List<SysUser> allUsers = userRepository.findAll();
        for (SysUser sysUser : allUsers) {
            for (SysRole role : sysUser.getRoles()) {
                System.out.println(role.getName().toString());
            }
        }
    }
}