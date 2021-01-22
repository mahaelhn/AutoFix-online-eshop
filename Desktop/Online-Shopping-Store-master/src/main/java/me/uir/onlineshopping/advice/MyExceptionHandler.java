package me.uir.onlineshopping.advice;

import me.uir.onlineshopping.exception.MyException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice

public class MyExceptionHandler {
    @ExceptionHandler(MyException.class)
    public String errorHandler(MyException ex, Model model){
        model.addAttribute("msg", ex.getMessage());
        return "/common/error";
    }

    @ExceptionHandler(Exception.class)
    public String errorHandler(Exception ex, Model model){
        model.addAttribute("msg", "Unknown Error!");
        return "/common/error";
    }


}
