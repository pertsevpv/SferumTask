package com.example.controllers

import com.example.dto.DealRequest
import com.example.entities.Product
import com.example.services.MarketService
import com.example.utils.Logger
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/market")
class MarketController(
    private val marketService: MarketService
) {

    @GetMapping
    fun market(): ResponseEntity<List<Product>> {
        Logger.query("Get products in stock query")

        val list = marketService.getInStock()
        return ResponseEntity(list, HttpStatus.OK)
    }

    @GetMapping("/deal")
    fun deal(@RequestBody jsonRequest: String): HttpStatus {
        Logger.query("Deal $jsonRequest query")

        val request = ObjectMapper().readValue(jsonRequest, DealRequest::class.java)
        try {
            marketService.deal(request)
        } catch (e: Exception) {
            return HttpStatus.BAD_REQUEST
        }

        return HttpStatus.OK
    }

}