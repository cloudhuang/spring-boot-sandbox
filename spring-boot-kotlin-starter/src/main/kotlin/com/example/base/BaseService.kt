package com.example.base

interface BaseService<T, ID> {
    @Throws(Exception::class)
    fun loadAll(): Iterable<T>

    @Throws(Exception::class)
    fun saveOrUpdate(domain: T): T

    @Throws(Exception::class)
    fun saveAndFlush(domain: T): T

    @Throws(Exception::class)
    fun saveOrUpdate(domainList: List<T>): Iterable<T>

    @Throws(Exception::class)
    fun delete(domain: T)

    @Throws(Exception::class)
    fun getById(id: ID): T

    fun flush()
}