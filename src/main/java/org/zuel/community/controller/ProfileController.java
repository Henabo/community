package org.zuel.community.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.zuel.community.model.User;
import org.zuel.community.service.IQuestionService;
import org.zuel.community.service.impl.QuestionServiceImpl;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ProfileController {
    @Autowired
    private IQuestionService questionService;


    @GetMapping("/profile/{action}")
    public String profileHomePage(@PathVariable(name = "action") String action,
                                  Model model,
                                  HttpServletRequest request,
                                  @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){
        User user = (User)request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }

        if ("question".equals(action)){
            model.addAttribute("section", "question");
            model.addAttribute("sectionName","提问");
            PageInfo pageInfo = questionService.selectByUserIdQuestionVOs(user.getId(), pageNum, pageSize);
            model.addAttribute("pageInfo", pageInfo);
        }else if ("answer".equals(action))
        {
            model.addAttribute("section", "answer");
            model.addAttribute("sectionName","回答");
        }
        return "profile";
    }
}
