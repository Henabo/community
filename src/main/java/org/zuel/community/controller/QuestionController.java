package org.zuel.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.zuel.community.service.ICommentService;
import org.zuel.community.service.IQuestionService;
import org.zuel.community.vo.CommentVO;
import org.zuel.community.vo.QuestionVO;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private ICommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id, Model model){
        questionService.updateViewCount(id);
        QuestionVO questionVO = questionService.selectById(id);
        List<CommentVO> commentVOS = commentService.readByQuestionId(id);

         model.addAttribute("comments", commentVOS);
        model.addAttribute("questionVO",questionVO);
        return "question";
    }
}
