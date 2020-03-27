package org.zuel.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.community.mapper.CommentMapper;
import org.zuel.community.model.Comment;
import org.zuel.community.service.ICommentService;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void add(Comment model) {
        model.setAddTime(System.currentTimeMillis());
        commentMapper.insertSelective(model);
    }
}
