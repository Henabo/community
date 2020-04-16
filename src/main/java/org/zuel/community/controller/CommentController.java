package org.zuel.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zuel.community.model.Comment;
import org.zuel.community.service.ICommentService;

@Controller
@ResponseBody
public class CommentController {

    @Autowired
    private ICommentService commentService;

    /*API，JSON的方式传值给前段*/

    /**
     * '@RequestBody'注释反序列化JSON为java对象
     * 接受的类型必须是application/json，因此如果用postman进行api测试的时候
     * 得在Header里面加入Content-Type:application/json
     * @param comment
     * @return
     */
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody Comment comment){
        return commentService.add(comment);
    }
}
