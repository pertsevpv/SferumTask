package com.example.controllers

import com.example.dto.DealRequest
import com.example.services.AccountService
import com.example.services.MarketService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpServletRequest

@Controller
class BookShopController(
    private val accountService: AccountService,
    private val marketService: MarketService
) {

    @GetMapping("/")
    fun mainPage(model: Model): String {
        val account = accountService.getAccount()
        model.addAttribute("account", account)

        val products = marketService.getInStock()
        model.addAttribute("products", products)

        model.addAttribute("dealRequest", DealRequest())

        return "main"
    }

    @PostMapping("/deal")
    fun deal(
        @ModelAttribute dealRequest: DealRequest,
        model: Model
    ): String {
        marketService.deal(dealRequest)
        return "redirect:/"
    }

}