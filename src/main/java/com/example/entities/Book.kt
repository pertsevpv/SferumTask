package com.example.entities

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@JsonAutoDetect
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id") var id: Long = 0,
    @JsonProperty("name") var name: String? = null,
    @JsonProperty("author") var author: String? = null,
)