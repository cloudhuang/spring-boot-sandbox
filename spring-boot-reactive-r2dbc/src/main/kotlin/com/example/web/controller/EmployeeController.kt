package com.example.web.controller

import com.example.entity.Employee
import com.example.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/employee")
class EmployeeController(@Autowired val employeeRepository: EmployeeRepository) {
    @GetMapping
    fun findAll(): Flux<Employee> {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable(name = "id") id: Int): Mono<Employee> {
        return employeeRepository.findById(id)
    }
}
