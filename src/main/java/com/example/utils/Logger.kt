package com.example.utils

import java.io.BufferedWriter
import java.io.File
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.util.*

object Logger {

    private val dateFormat = SimpleDateFormat("HH:mm:ss dd.MM.yyyy")
    private var writer = BufferedWriter(OutputStreamWriter(System.out))

    enum class LogType {
        QUERY, ERROR, SUCCESS, INFO
    }

    fun changeOutputType(file: File) {
        if (!file.exists()) file.createNewFile()
        changeOutputType(file.outputStream())
    }

    fun <T> logSuccessOrError(successMsg: String, block: () -> T): T {
        try {
            return block().also { success(successMsg) }
        } catch (e: Exception) {
            error(e.message ?: e.toString())
            throw e
        }
    }

    fun <T> logIfError(block: () -> T): T {
        try {
            return block()
        } catch (e: Exception) {
            error(e.message ?: e.toString())
            throw e
        }
    }

    fun success(msg: String) = log(LogType.SUCCESS, msg)
    fun error(msg: String) = log(LogType.ERROR, msg)
    fun query(msg: String) = log(LogType.QUERY, msg)
    fun info(msg: String) = log(LogType.INFO, msg)

    private fun log(type: LogType, message: String) {
        val date = dateFormat.format(Date())
        val log = "$date [$type] \t $message"

        writer.apply {
            write(log)
            newLine()
            flush()
        }
    }

    private fun changeOutputType(outputStream: OutputStream) {
        this.writer = BufferedWriter(OutputStreamWriter(outputStream))
    }

}
