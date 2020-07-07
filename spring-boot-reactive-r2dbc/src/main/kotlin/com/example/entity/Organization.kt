package com.example.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("organization")
data class Organization(@Id val id: Int? = null, val name: String = "", val description: String = "") {

}