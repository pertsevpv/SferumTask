package com.example.repository

import com.example.entities.Basket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository("BasketRepository")
interface BasketRepository : JpaRepository<Basket, Long> {

}