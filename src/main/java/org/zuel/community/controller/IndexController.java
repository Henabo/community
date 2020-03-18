package org.zuel.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zuel.community.model.Question;
import org.zuel.community.model.User;
import org.zuel.community.service.IUserService;
import org.zuel.community.service.impl.QuestionService;
import org.zuel.community.vo.IndexQuestionVO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;



    @GetMapping("/")
    public String hello(HttpServletRequest request,
                        Model model,
                        @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
                        @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){


        if(pageNum <= 0){
            pageNum = 1;
        };
        PageInfo pageInfo = questionService.selectIndexQuestionVOs(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "index";
    }

}