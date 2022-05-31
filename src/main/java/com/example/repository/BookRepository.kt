package com.example.repository

import com.example.entities.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository("BookRepository")
interface BookRepository : JpaRepository<Book, Long> {

    override fun findAll(): List<Book>

    override fun <S : Book?> save(entity: S): S
}