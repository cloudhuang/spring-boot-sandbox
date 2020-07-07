package com.example.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("employee")
data class Employee(val name: String = "", val age: Int = 0, @Id val id: Int? = null) {
}