package org.zuel.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zuel.community.model.Question;
import org.zuel.community.model.User;
import org.zuel.community.service.IQuestionService;
import org.zuel.community.service.IUserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IUserService userService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(name = "title") String title,
                            @RequestParam(name = "content")String content,
                            @RequestParam(name = "tag")String tag,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("title",title);
        model.addAttribute("content", content);
        model.addAttribute("tag", tag);
        if (title == null || title == ""){
            model.addAttribute("error","标题，标题不能为空！！！");
            return "publish";
        }
        if (content == null || content == ""){
            model.addAttribute("error","问题详情，问题详情不能为空！！！");
            return "publish";
        }
        if (tag == null || tag == ""){
            model.addAttribute("error","标签，标签不能为空！！！");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");

        if (user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }


        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setTag(tag);
        question.setUserId(user.getId());
        questionService.add(question);
        return "redirect:/";
    }
}
