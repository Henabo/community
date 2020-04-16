package org.zuel.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.community.mapper.CommentMapper;
import org.zuel.community.mapper.QuestionMapper;
import org.zuel.community.mapper.UserMapper;
import org.zuel.community.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 用户引用，根据ids列表查找user
     */
    public Map<Integer, User> refUser(List<Integer> ids){
        Map<Integer, User> refMap = new HashMap<>();
        if(ids != null && ids.size() != 0){
            UserExample example = new UserExample();
            example.setDistinct(true);
            example.createCriteria().andIdIn(ids);
            List<User> models = userMapper.selectByExample(example);
            models.forEach(model ->{
                refMap.put(model.getId(), model);
            });
        }
        return refMap;
    }

    /**
     * 问题引，根据ids列表查找question
     */
    public Map<Integer, Question> refQuestion(List<Integer> ids){
        Map<Integer, Question> refMap = new HashMap<>();
        if(ids != null && ids.size() != 0){
            QuestionExample example = new QuestionExample();
            example.setDistinct(true);
            example.createCriteria().andIdIn(ids);
            List<Question> models =questionMapper.selectByExample(example);
            models.forEach(model -> {
                refMap.put(model.getId(), model);
            });
        }
        return refMap;
    }

    /**
     * comment引用，根据ids列表查找comment
     * @param ids
     * @return
     */
    public Map<Integer, Comment> refComment(List<Integer> ids){
        Map<Integer, Comment> refMap = new HashMap<>();
        if (ids != null && ids.size() != 0) {
            CommentExample example = new CommentExample();
            example.setDistinct(true);
            example.createCriteria().andIdIn(ids);
            List<Comment> models = commentMapper.selectByExample(example);
            models.forEach(model -> {
                refMap.put(model.getId(), model);
            });
        }
        return refMap;
    }
}
