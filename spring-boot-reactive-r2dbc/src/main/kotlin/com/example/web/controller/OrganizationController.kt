package com.example.web.controller

import com.example.repository.OrganizationRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/organizations")
class OrganizationController(val organizationRepository: OrganizationRepository) {

}