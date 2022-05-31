package com.example.entities

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@JsonAutoDetect
data class Account(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id") var id: Long = 0,
    @JsonProperty("balance") var balance: Long = 0,
    @JsonProperty("products") @OneToMany(
        cascade = [CascadeType.MERGE],
        fetch = FetchType.EAGER
    ) var products: MutableList<Basket>? = mutableListOf(),
)