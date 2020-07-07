package com.example.repository

import com.example.entity.Employee
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface EmployeeRepository : R2dbcRepository<Employee, Int>