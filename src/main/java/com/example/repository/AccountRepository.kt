package com.example.repository

import com.example.entities.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository("AccountRepository")
interface AccountRepository : JpaRepository<Account, Long> {

    override fun findAll(): MutableList<Account>

    override fun findById(id: Long): Optional<Account>

    override fun <S : Account?> save(entity: S): S
}