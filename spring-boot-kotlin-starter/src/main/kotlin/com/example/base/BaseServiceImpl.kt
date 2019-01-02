package com.example.base

import org.springframework.data.jpa.repository.JpaRepository

abstract class BaseServiceImpl<T, ID>(private val jpaRepository: JpaRepository<T, ID>) : BaseService<T, ID> {

    override fun loadAll(): Iterable<T> {
        return jpaRepository.findAll()
    }

    override fun saveOrUpdate(domain: T): T {
        return jpaRepository.save(domain)
    }

    override fun saveAndFlush(domain: T): T {
        return jpaRepository.saveAndFlush(domain)
    }

    override fun saveOrUpdate(domainList: List<T>): Iterable<T> {
        return jpaRepository.saveAll(domainList)
    }

    override fun delete(domain: T) {
        jpaRepository.delete(domain)
    }

    override fun getById(id: ID): T {
        return jpaRepository.getOne(id)
    }

    override fun flush() {
        jpaRepository.flush()
    }
}