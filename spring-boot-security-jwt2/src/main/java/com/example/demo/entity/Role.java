package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    private String name;

    @ManyToMany(targetEntity = Authority.class, fetch = FetchType.EAGER)
    @JoinTable(name = "roles_authorities", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    private List<Authority> authorities = new ArrayList<>();

}