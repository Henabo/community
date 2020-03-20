package org.zuel.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.zuel.community.service.IQuestionService;
import org.zuel.community.vo.QuestionVO;

@Controller
public class QuestionController {
    @Autowired
    private IQuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model){
        questionService.updateViewCount(id);
        QuestionVO questionVO = questionService.selectById(id);

        model.addAttribute("questionVO",questionVO);
        return "question";
    }
}
