package com.example.services

import com.example.entities.Product
import com.example.repository.ProductRepository
import com.example.utils.Logger
import org.springframework.stereotype.Service
import java.util.*

@Service("ProductService")
class ProductService(
    private val productRepository: ProductRepository
) {

    fun save(product: Product?): Product? {
        return Logger.logIfError {
            productRepository.save(product).also {
                Logger.success("Product $it saved")
            }
        }
    }

    fun findAll(): List<Product> {
        return Logger.logIfError { productRepository.findAll() }
    }

    fun findById(id: Long): Optional<Product> {
        return Logger.logIfError { productRepository.findById(id) }
    }

}

//curl -X POST 'http://localhost:8080/market/deal' -H 'Content-Type: application/json' -d '{"id": 0, "amount": 2}'