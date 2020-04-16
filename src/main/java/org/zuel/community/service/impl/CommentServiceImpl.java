package org.zuel.community.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zuel.community.mapper.CommentMapper;
import org.zuel.community.model.Comment;
import org.zuel.community.model.CommentExample;
import org.zuel.community.model.Question;
import org.zuel.community.model.User;
import org.zuel.community.service.ICommentService;
import org.zuel.community.service.IQuestionService;
import org.zuel.community.util.ResponseUtil;
import org.zuel.community.vo.CommentVO;
import org.zuel.community.vo.QuestionVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private CommonServiceImpl commonService;

    /**
     * 添加评论，并使问题评论数量+1
     * @param model
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object add(Comment model) {
        if(com.alibaba.druid.util.StringUtils.isEmpty(model.getContent())){
            return ResponseUtil.commentContentIsEmpty();
        }
        model.setAddTime(System.currentTimeMillis());
        commentMapper.insertSelective(model);
        questionService.updateCommentCount(model.getQuestionId());
        return ResponseUtil.ok();
    }

    /**
     * 根据quesitonId查找评论，（没删除，没屏蔽）
     * @param questionId
     * @return CommentVO类
     */
    @Override
    public List<CommentVO> readByQuestionId(Integer questionId) {
        List<CommentVO> commentVOS = models2vos(selectByQuestionId(questionId));
        return commentVOS;
    }

    /**
     * 根据quesitonId查找评论，（没删除，没屏蔽）
     * @param questionId
     * @return
     */
    @Override
    public List<Comment> selectByQuestionId(Integer questionId) {
        List<Comment> comments = commentMapper.selectByExampleWithBLOBs(selectByQuestionIdExample(questionId));
        return comments;
    }
    private CommentExample selectByQuestionIdExample(Integer questionId){
        CommentExample example = new CommentExample();
        example.setDistinct(true);
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andShieldedEqualTo(false);
        criteria.andDeletedEqualTo(false);
        criteria.andQuestionIdEqualTo(questionId);
        example.setOrderByClause("add_time desc");
        return example;
    }


    /**
     * models to vos
     * @param models
     * @return
     */
    private List<CommentVO> models2vos(List<Comment> models){
        List<CommentVO> vos = new ArrayList<>();
        List<Integer> userIds = new ArrayList<>();
        models.forEach(model ->{
            //去除重复的userId，防止后面的Map中key的重复
            if(!userIds.contains(model.getUserId())){
                userIds.add(model.getUserId());
            }
            CommentVO vo = new CommentVO();
            BeanUtils.copyProperties(model, vo);
            vo.setAvatorUrl(null);
            vo.setUserName(null);
            vos.add(vo);
        });
        //用hashMap去检索效率更高
        Map<Integer, User> refUser = commonService.refUser(userIds);
        vos.forEach(vo ->{
            vo.setUserName(refUser.get(vo.getUserId()).getUserName());
            vo.setAvatorUrl(refUser.get(vo.getUserId()).getAvatorUrl());
        });
        return vos;
    }

}
