package org.zuel.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.community.mapper.QuestionMapper;
import org.zuel.community.mapper.UserMapper;
import org.zuel.community.model.Question;
import org.zuel.community.model.QuestionExample;
import org.zuel.community.model.User;
import org.zuel.community.service.IQuestionService;
import org.zuel.community.vo.QuestionVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommonServiceImpl commonService;


    @Override
    public void add(Question model) {
        model.setAddTime(System.currentTimeMillis());
        model.setAnswerTime(model.getAddTime());
        questionMapper.insertSelective(model);
    }

    /**
     * 查询问题，(没删除)（不考虑屏蔽）
     * 最新回答问题时间倒序
     * @return
     */
    @Override
    public List<Question> select() {
        List<Question> questionList = questionMapper.selectByExampleWithBLOBs(selectExample());
        return questionList;
    }
    private QuestionExample selectExample(){
        QuestionExample example = new QuestionExample();
        example.setDistinct(true);
        QuestionExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return example;
    }

    /**
     * 查询问题，（没删除、没屏蔽）
     * 最新回答问题时间倒序
     * @return
     */
    @Override
    public List<Question> selectQuestion() {
        List<Question> questionList = questionMapper.selectByExampleWithBLOBs(selectQuestionExample());
        return questionList;
    }
    private QuestionExample selectQuestionExample(){
        QuestionExample example = new QuestionExample();
        example.setDistinct(true);
        QuestionExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andDeletedEqualTo(false);
        example.setOrderByClause("answer_time desc");
        return example;
    }


    /**
     * PageInfo的特殊原因，如果需要查找多少页只能在原表上进行查找
     * 所以只能在原标上进行查找之后，再将model改为vo类型
     * 查找原表顺序为回答时间倒序
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo selectQuestionVOs(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Question> questionList = selectQuestion();
        PageInfo pageInfo = new PageInfo(questionList);
        List<QuestionVO> vos = models2vos(questionList);
        pageInfo.setList(vos);
        return pageInfo;
    }


    /**
     * 查询出questionVOs，（没删除、没屏蔽）
     * 回答时间倒序
     * @return
     */
    @Override
    public List<QuestionVO> selectQuestionVOs() {
        List<Question> questionList = questionMapper.selectByExampleWithBLOBs(selectQuestionVOsExample());
        return models2vos(questionList);
    }
    private QuestionExample selectQuestionVOsExample(){
        QuestionExample example = new QuestionExample();
        example.setDistinct(true);
        QuestionExample.Criteria criteria = example.createCriteria();
        criteria.andShieldedEqualTo(false);
        criteria.andDeletedEqualTo(false);
        example.setOrderByClause("answer_time desc");
        return example;
    }

    /**
     * 查找user所有提出的问题（没删除、没屏蔽）
     * 回答时间倒序？（应该是提出问题的倒序）已改
     * @param userId
     * @return
     */
    @Override
    public List<Question> selectByUserId(Integer userId) {
        List<Question> questionList = questionMapper.selectByExampleWithBLOBs(selectByUserIdExample(userId));
        return questionList;
    }
    private QuestionExample selectByUserIdExample(Integer userId){
        QuestionExample example = new QuestionExample();
        example.setDistinct(true);
        QuestionExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andShieldedEqualTo(false);
        criteria.andUserIdEqualTo(userId);
        example.setOrderByClause("add_time desc");
        return example;
    }

    /**
     * 用户提出问题分页处理，应该先在原表上进行查找
     * 找到所有question，然后分页就好了，我写的麻烦了但也没问题
     * 找到user提出的所有问题（没删除、没屏蔽）
     * 添加问题时间倒序
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo selectByUserIdQuestionVOs(Integer userId, Integer pageNum, Integer pageSize) {
        List<Question> questiontempList = selectByUserId(userId);
        Integer total = questiontempList.size();
        if (pageNum < 1){
            pageNum = 1;
        }
        if (pageNum > total){
            pageNum = total;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Question> questionList = selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(questionList);
        List<QuestionVO> vos = models2vos(questionList);
        pageInfo.setList(vos);
        pageInfo.setPageNum(pageNum);
        Integer pages;
        if(total%pageSize == 0){
            pages = total/pageSize;
        }else {
            pages = total/pageSize +1;
        }
        pageInfo.setPages(pages);
        if (pageNum > 1){
            pageInfo.setHasPreviousPage(true);
        }else {
            pageInfo.setHasPreviousPage(false);
        }
        if (pageNum < pages){
            pageInfo.setHasNextPage(true);
        }else {
            pageInfo.setHasNextPage(false);
        }
        return pageInfo;
    }

    /**
     * 根据id查找vo（不考虑删除，不考虑屏蔽）
     * @param id
     * @return
     */
    @Override
    public QuestionVO selectById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionVO questionVO = model2vo(question);
        return questionVO;
    }

    /**
     * 阅读量+1
     * @param id
     */
    @Override
    public void updateViewCount(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        question.setViewCount( question.getViewCount() + 1);
        question.setUpdateTime(System.currentTimeMillis());
        questionMapper.updateByPrimaryKeySelective(question);
    }

    /**
     * 回答数+1
     * @param id
     */
    @Override
    public void updateCommentCount(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        question.setCommentCount(question.getCommentCount() + 1);
        question.setUpdateTime(System.currentTimeMillis());
        questionMapper.updateByPrimaryKeySelective(question);
    }


    /**
     * model to vo
     * @param model
     * @return
     */
    private QuestionVO model2vo(Question model){
        QuestionVO vo = new QuestionVO();
        BeanUtils.copyProperties(model, vo);
        if(vo.getUserId() != null){
            User user = userMapper.selectByPrimaryKey(vo.getUserId());
            if (user != null) {
                vo.setUser(user);
            }
        }
        return vo;
    }

    /**
     * models to vos
     * @param models
     * @return
     */
    private List<QuestionVO> models2vos(List<Question> models){
        List<QuestionVO> vos = new ArrayList<>();
        List<Integer> userIds = new ArrayList<>();
        models.forEach(model ->{
            //去除重复的userId，防止后面的Map中key的重复
            if(!userIds.contains(model.getUserId())){
                userIds.add(model.getUserId());
            }
            QuestionVO vo = new QuestionVO();
            BeanUtils.copyProperties(model, vo);
            vo.setUser(null);
            vos.add(vo);
        });
        //用hashMap去检索效率更高
        Map<Integer, User> refUser = commonService.refUser(userIds);
        vos.forEach(vo ->{
            vo.setUser(refUser.get(vo.getUserId()));
        });
        return vos;
    }



}
