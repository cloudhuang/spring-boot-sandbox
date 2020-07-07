package com.example.repository

import com.example.entity.Employee
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class EmployeeRepositoryTest(@Autowired val employeeRepository: EmployeeRepository) {
    @Test
    fun `test save employee`() {
        var emp = Employee(name = "Tom ", age = 38)
        employeeRepository.save(emp).log().subscribe()

        emp = Employee(name = "Bruce", age = 48)
        employeeRepository.save(emp).log().subscribe()

        val count = employeeRepository.findAll().count().block()
        println(count)
        employeeRepository.findAll().map { e -> println(e.id) }.log().subscribe()
    }
}