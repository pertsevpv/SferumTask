package com.example.repository

import com.example.entities.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository("ProductRepository")
interface ProductRepository : JpaRepository<Product, Long> {

    override fun findAll(): List<Product>

    override fun <S : Product?> save(entity: S): S
}