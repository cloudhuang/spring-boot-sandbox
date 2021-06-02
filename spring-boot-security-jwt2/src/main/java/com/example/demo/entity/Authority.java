package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authority extends BaseEntity  implements GrantedAuthority {
    private String url;
    private String name;
    private long pid;
    private String description;

    @Override
    public String getAuthority() {
        return name;
    }
}