package org.zuel.community.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zuel.community.cache.TagCache;
import org.zuel.community.model.Question;
import org.zuel.community.model.User;
import org.zuel.community.service.IQuestionService;
import org.zuel.community.service.IUserService;
import org.zuel.community.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IUserService userService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model){
        QuestionVO questionVO = questionService.selectById(id);
        model.addAttribute("title",questionVO.getTitle());
        model.addAttribute("content", questionVO.getContent());
        model.addAttribute("tag", questionVO.getTag());
        model.addAttribute("questionVO", questionVO);
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("tags", TagCache.get());
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
        model.addAttribute("tags", TagCache.get());
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

        String invalid = TagCache.filterInvalid(tag);
        if(StringUtils.isNotBlank(invalid)){
            model.addAttribute("error", "输入非法标签" + invalid);
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
//        questionService.addOrUpdate(question);
        questionService.updateOrAdd(question);
        return "redirect:/";
    }
}
