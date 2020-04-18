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
     * @return Object
     */
    Object add(Comment model);

    /**
     * QuestionId查找评论，（没删除、没屏蔽）
     * @param id
     * @param type
     * @return
     */
    List<CommentVO> readByTargetId(Integer id, Boolean type);

    /**
     * QuestionId查找评论，（没删除、没屏蔽）
     * @param questionId
     * @return
     */
    List<Comment> selectByQuestionId(Integer questionId, Boolean type);

    /**
     * 根据comment查找二级评论
     * @param id
     * @param type
     * @return
     */
    Object readByCommentId(Integer id, Boolean type);

    /**
     * 评论数+1
     * @param id
     * @return
     */
    void updateCommentCount(Integer id);
}
