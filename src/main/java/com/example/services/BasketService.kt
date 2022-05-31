package com.example.services

import com.example.entities.Basket
import com.example.repository.BasketRepository
import com.example.utils.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("BasketService")
class BasketService(
    @Autowired private val basketRepository: BasketRepository
) {

    fun save(basket: Basket): Basket {
        return Logger.logIfError {
            basketRepository.save(basket).also {
                Logger.success("Basket $it saved")
            }
        }
    }

    fun findByProductId(productId: Long): Basket? {
        return Logger.logIfError {
            val allBaskets = basketRepository.findAll()
            allBaskets.find { it.product?.id == productId }
        }
    }

}