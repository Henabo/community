package org.zuel.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zuel.community.model.User;
import org.zuel.community.service.IUserService;
import org.zuel.community.service.impl.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;

    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size){
        Cookie[] cookies = request.getCookies();

        for(Cookie cookie : cookies){
            if("herb_userName".equals(cookie.getName())){
                String herb_userName = cookie.getValue();
                User user = userService.selectByUserName(herb_userName);
                if( user == null){
                    request.getSession().setAttribute("user",null);
                } else {
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }

//        PaginationDTO pagination = questionService.list(page,size);
//
//        model.addAttribute("pagination",pagination);

        return "index";
    }

}