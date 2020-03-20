package org.zuel.community.service;

import com.github.pagehelper.PageInfo;
import org.zuel.community.model.Question;
import org.zuel.community.vo.QuestionVO;

import java.util.List;

public interface IQuestionService {

    void add(Question model);

    List<Question> select();

    List<Question> selectQuestion();

    PageInfo<QuestionVO>selectQuestionVOs(Integer pageNum, Integer pageSize);

    List<QuestionVO> selectQuestionVOs();

    List<Question> selectByUserId(Integer userId);

    PageInfo<QuestionVO> selectByUserIdQuestionVOs(Integer userId, Integer pageNum, Integer pageSize);

    QuestionVO selectById(Integer id);

    void updateViewCount(Integer id);
}
