package com.example.services

import com.example.dto.DealRequest
import com.example.entities.Basket
import com.example.entities.Product
import com.example.exceptions.NoMoneyException
import com.example.exceptions.NoSuchProductException
import com.example.exceptions.ProductNotFoundException
import com.example.utils.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("MarketService")
class MarketService(
    @Autowired private val productService: ProductService,
    @Autowired private val accountService: AccountService,
    @Autowired private val basketService: BasketService,
) {

    fun getInStock(): List<Product> {
        return productService.findAll().filter { it.amountInMarket > 0 }
    }

    fun deal(request: DealRequest) {
        Logger.logSuccessOrError("Deal Request $request done") { makeDeal(request) }
    }

    private fun makeDeal(request: DealRequest) {
        val product =
            productService.findById(request.productId).orElseThrow { ProductNotFoundException(request.productId) }

        if (request.productId < 0) throw IllegalArgumentException("productId ${request.productId} is negative")
        if (request.amount < 0) throw IllegalArgumentException("Product amount should be positive")
        if (request.amount == 0L) return

        if (product.amountInMarket - request.amount < 0) throw NoSuchProductException(
            request.amount,
            product.amountInMarket
        )

        val money = request.amount * product.price
        val account = accountService.getAccount()
        if (account.balance - money < 0) throw NoMoneyException(money, account.balance)

        product.amountInMarket -= request.amount
        account.balance -= money

        var basket = basketService.findByProductId(product.id)
        if (basket == null) {
            basket = Basket(amount = request.amount, product = product)
            basket = basketService.save(basket)
            account.products?.add(basket)
        } else {
            basket.amount += request.amount
            basketService.save(basket)
        }

        productService.save(product)
        accountService.save(account)
    }

}