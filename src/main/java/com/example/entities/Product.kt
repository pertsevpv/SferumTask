package com.example.entities

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@JsonAutoDetect
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id") var id: Long = 0,
    @JsonProperty("price") var price: Long = 0,
    @JsonProperty("amount") var amountInMarket: Long = 0,
    @JsonProperty("book") @OneToOne var book: Book? = null
)