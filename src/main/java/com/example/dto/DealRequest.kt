package com.example.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty

@JsonAutoDetect
data class DealRequest(
    @JsonProperty("id") val productId: Long = 0,
    @JsonProperty("amount") val amount: Long = 0,
)