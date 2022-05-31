package com.example.entities

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class Basket(
    @Id @GeneratedValue
    @JsonProperty("id") var id: Long = 0,
    @JsonProperty("amount") var amount: Long = 0,
    @JsonProperty("product") @OneToOne var product: Product? = null
)