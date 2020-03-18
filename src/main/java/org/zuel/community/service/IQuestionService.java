package org.zuel.community.service;

import com.github.pagehelper.PageInfo;
import org.zuel.community.model.Question;
import org.zuel.community.vo.IndexQuestionVO;

import java.util.List;

public interface IQuestionService {

    void add(Question model);

    List<Question> select();

    List<Question> selectIndexQuestion();

    PageInfo<IndexQuestionVO>selectIndexQuestionVOs(Integer pageNum, Integer pageSize);

    List<IndexQuestionVO> selectIndexQuestionVOs();

    List<Question> selectByUserId(Integer userId);

    PageInfo<IndexQuestionVO> selectByUserIdQuestionVOs(Integer userId, Integer pageNum, Integer pageSize);
}
