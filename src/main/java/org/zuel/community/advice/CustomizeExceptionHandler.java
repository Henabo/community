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

    /**
     * HttpServletRequest request加入参数，然后判断请求格式，如果是json格式则xxx
     * 如果不是json则返回ModelAndView（error）
     * @param request
     * @param e
     * @param model
     * @return
     */
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model){
        if (e instanceof CustomizeException){
            model.addAttribute("message", e.getMessage());
        }else {
            model.addAttribute("message", "服务器出错了！！！赶紧通知HNB，修复BUG！！！");
        }
        return new ModelAndView("error");
    }
}
