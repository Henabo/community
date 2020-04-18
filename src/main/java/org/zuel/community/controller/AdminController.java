package org.zuel.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Nabo He
 * @Date: 2020/4/17 5:23 下午
 */
@Controller
@ResponseBody
public class AdminController {

    @RequestMapping(value = "/adminIndex", method = RequestMethod.GET)
    public Object adminIndex(HttpServletRequest request){
//         request.getCookies("herb_userName")
        return null;
    }
}
