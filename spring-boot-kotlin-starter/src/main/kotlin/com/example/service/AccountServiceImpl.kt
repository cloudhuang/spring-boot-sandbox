package com.example.service

import com.example.entity.Account
import com.example.repository.AccountRepository
import com.example.api.AccountService
import com.example.base.BaseServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AccountServiceImpl(accountRepository: AccountRepository) : BaseServiceImpl<Account, Long>(accountRepository), AccountService {

}