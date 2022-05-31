package com.example.services

import com.example.entities.Book
import com.example.repository.BookRepository
import com.example.utils.Logger
import org.springframework.stereotype.Service

@Service("BookService")
class BookService(
    private val bookRepository: BookRepository
) {
    fun save(book: Book?): Book? {
        return Logger.logIfError {
            bookRepository.save(book).also {
                Logger.success("Book $it saved")
            }
        }
    }

    fun findAll(): List<Book> {
        return Logger.logIfError { bookRepository.findAll() }
    }
}