package com.example.repository

import com.example.entity.Organization
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface OrganizationRepository : R2dbcRepository<Organization, Int>