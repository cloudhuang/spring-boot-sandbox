package com.example.respository;

import com.example.entity.RoleName;
import com.example.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
    Optional<SysRole> findByName(RoleName roleName);
}
