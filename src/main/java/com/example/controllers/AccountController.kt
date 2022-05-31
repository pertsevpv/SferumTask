package com.example.controllers

import com.example.entities.Account
import com.example.repository.AccountRepository
import com.example.services.AccountService
import com.example.utils.Logger
import org.apache.juli.logging.Log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class AccountController(
    @Autowired private val accountService: AccountService
) {

    @GetMapping
    fun getAccount(): ResponseEntity<Account> {
        Logger.query("Get account query")

        val account = accountService.getAccount()
        return ResponseEntity(account, HttpStatus.OK)
    }

}