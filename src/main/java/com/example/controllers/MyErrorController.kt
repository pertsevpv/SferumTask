package com.example.controllers

import com.example.exceptions.BookShopException
import com.example.utils.Logger
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest


@Controller
class MyErrorController : ErrorController {

    @RequestMapping("/error")
    fun handleError(
        request: HttpServletRequest,
        model: Model
    ): String {
        val throwable = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION) as Throwable?

        val status = HttpStatus.valueOf(400)
        
        model.addAttribute("status", "${status.value()} - ${status.reasonPhrase}")
        model.addAttribute("description", throwable?.message ?: throwable.toString())

        Logger.error(throwable?.message ?: throwable.toString())

        return "error"
    }

}