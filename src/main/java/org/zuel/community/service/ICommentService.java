package org.zuel.community.service;

import org.zuel.community.model.Comment;
import org.zuel.community.vo.CommentVO;

import java.util.List;

/**
 * @author henabo
 */
public interface ICommentService {
    /**
     * 添加评论，并使问题评论数量+1
     * @param model
     */
    Object add(Comment model);

    /**
     * QuestionId查找评论，（没删除、没屏蔽）
     * @param id
     * @return
     */
    List<CommentVO> readByQuestionId(Integer id);

    /**
     * QuestionId查找评论，（没删除、没屏蔽）
     * @param questionId
     * @return
     */
    List<Comment> selectByQuestionId(Integer questionId);
}
