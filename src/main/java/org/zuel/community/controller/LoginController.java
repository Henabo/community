package org.zuel.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zuel.community.model.User;
import org.zuel.community.service.IUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String loginHomePage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "userName",required = true) String userName,
                        @RequestParam(name = "password", required = true) String password,
                        HttpServletRequest request,
                        HttpServletResponse response){
         User user = userService.login(userName, password);
         if(user ==null){
             return "login";
         }else{
             response.addCookie(new Cookie("herb_userName",userName));
//             request.getSession().setAttribute("user",userList.get(0));
             return "redirect:/";
         }
    }
}
