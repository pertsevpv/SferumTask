package com.example

import com.example.dto.InitialValue
import com.example.repository.AccountRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import com.example.repository.BookRepository
import com.example.repository.ProductRepository
import com.example.services.AccountService
import com.example.services.BookService
import com.example.services.ProductService
import com.example.utils.Logger
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.SpringApplication
import java.io.File

@SpringBootApplication
open class BookShopApplication

fun main(args: Array<String>) {
    val context = SpringApplication.run(BookShopApplication::class.java, *args)

    if (args.isNotEmpty()) {
        val initialFilePath: String = args[0]
        if (args.size >= 2) {
            Logger.changeOutputType(File(args[1]))
        }

        val initialFile = File(initialFilePath)
        if (!initialFile.exists()) {
            Logger.info("Initial file $initialFilePath doesn't exist")
        }
        try {
            val initial = ObjectMapper().readValue(initialFile, InitialValue::class.java)

            val bookService = context.getBean("BookService") as BookService
            val productService = context.getBean("ProductService") as ProductService
            val accountService = context.getBean("AccountService") as AccountService

            initial.products.forEach { product ->
                product.book = bookService.save(product.book)
                productService.save(product)
            }

            accountService.save(initial.account)
            Logger.info("Initial values saved")
        } catch (e: Exception) {
            e.printStackTrace()
            Logger.error("Error while reading initial file : ${e.message}")
        }
    }
}