package com.example.dto

import com.example.entities.Account
import com.example.entities.Product
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty

@JsonAutoDetect
data class InitialValue(
    @JsonProperty("account")var  account: Account? = null,
    @JsonProperty("products") var products: List<Product> = emptyList()
)