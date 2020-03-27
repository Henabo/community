package org.zuel.community.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zuel.community.model.Comment;
import org.zuel.community.service.ICommentService;
import org.zuel.community.vo.CommentVO;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentVO commentVO){
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentVO, comment);
        commentService.add(comment);
        Map<Object, Object> obj = new HashMap<>();
        obj.put("message", "成功！！");
        return obj;
    }
}
