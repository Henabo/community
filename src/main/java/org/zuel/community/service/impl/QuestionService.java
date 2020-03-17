package org.zuel.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.community.mapper.QuestionMapper;
import org.zuel.community.mapper.UserMapper;
import org.zuel.community.model.Question;
import org.zuel.community.service.IQuestionService;

@Service
public class QuestionService implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public void add(Question model) {
        model.setAddTime(System.currentTimeMillis());
        model.setAnswerTime(model.getAddTime());
        questionMapper.insertSelective(model);
    }
}
