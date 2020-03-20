package org.zuel.community.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.zuel.community.exception.CustomizeException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model){
        if (e instanceof CustomizeException){
            model.addAttribute("message", e.getMessage());
        }else {
            model.addAttribute("message", "WDNMD，服务器出错了！！！赶紧通知HNB，修复BUG！！！");
        }
        return new ModelAndView("error");
    }
}
