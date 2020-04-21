package org.zuel.community.service;

import com.github.pagehelper.PageInfo;
import org.zuel.community.model.Question;
import org.zuel.community.vo.QuestionVO;

import java.util.List;

public interface IQuestionService {

    /**
     * 添加问题
     * @param model
     */
    void add(Question model);

    /**
     * 整合添加问题和修改问题操作
     * @param model
     */
    void updateOrAdd(Question model);

    /**
     * 更新问题
     * @param model
     */
    void update(Question model);
    /**
     * 查询出未删除的数据，（不考虑屏蔽）
     * @return
     */
    List<Question> select();

    /**
     * 查询没有删除、没有屏蔽的所有问题列表
     * @return
     */
    List<Question> selectQuestion();

    /**
     * 查询出分页的问题（没删除、没屏蔽）
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<QuestionVO>selectQuestionVOs(Integer pageNum, Integer pageSize);

    /**
     * 查询出没有删除、没有屏蔽的QuestionVO对象
     * @return
     */
    List<QuestionVO> selectQuestionVOs();

    /**
     * 通过UserId查询问题
     * @param userId
     * @return
     */
    List<Question> selectByUserId(Integer userId);

    /**
     * 通过用户id查询分页信息，用于用户查询自己的所有提出的问题
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<QuestionVO> selectByUserIdQuestionVOs(Integer userId, Integer pageNum, Integer pageSize);

    /**
     * 通过id查询问题
     * @param id
     * @return
     */
    QuestionVO selectById(Integer id);

    /**
     * 阅读数量+1
     * @param id
     */
    void updateViewCount(Integer id);

    /**
     * 评论数+1
     * @param id
     */
    void updateCommentCount(Integer id);

    /**
     * 通过标签查找问题
     * @param tags
     * @return
     */
    List<QuestionVO> selectByTags(Integer id, String tags);
}
