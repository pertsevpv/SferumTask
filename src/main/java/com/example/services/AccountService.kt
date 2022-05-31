package com.example.services

import com.example.entities.Account
import com.example.repository.AccountRepository
import com.example.utils.Logger
import org.springframework.stereotype.Service

@Service("AccountService")
class AccountService(
    private val accountRepository: AccountRepository
) {

    fun save(account: Account?): Account? {
        return Logger.logIfError {
            accountRepository.save(account).also {
                Logger.success("Account $it saved")
            }
        }
    }

    fun findAll(): List<Account> {
        return Logger.logIfError { accountRepository.findAll() }
    }

    fun getAccount(): Account {
        return Logger.logIfError { accountRepository.findAll()[0] }
    }

}