package com.example.service.impl

import com.example.api.AccountService
import com.example.api.ArticleService
import com.example.entity.Account
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class AccountServiceImplTest {
    @Autowired
    lateinit var accountService: AccountService
    @Autowired
    lateinit var articleService: ArticleService

    @Test
    fun save() {
        val account = Account()
        account.name = "Liping Huang"
        accountService.saveOrUpdate(account)

        val loadAll = accountService.loadAll()
        println(loadAll.count())
    }

    @Test
    fun loadAll() {
        val all = articleService.loadAll()
        println(all.count())
    }
}