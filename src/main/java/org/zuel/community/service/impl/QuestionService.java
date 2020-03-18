package org.zuel.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.community.mapper.QuestionMapper;
import org.zuel.community.mapper.UserMapper;
import org.zuel.community.model.Question;
import org.zuel.community.model.QuestionExample;
import org.zuel.community.model.User;
import org.zuel.community.service.IQuestionService;
import org.zuel.community.vo.IndexQuestionVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommonService commonService;


    @Override
    public void add(Question model) {
        model.setAddTime(System.currentTimeMillis());
        model.setAnswerTime(model.getAddTime());
        questionMapper.insertSelective(model);
    }

    /**
     * 查询出未删除的数据，（不考虑屏蔽）
     * @return
     */
    @Override
    public List<Question> select() {
        List<Question> questionList = questionMapper.selectByExampleWithBLOBs(selectExample());
        return questionList;
    }
    private QuestionExample selectExample(){
        QuestionExample example = new QuestionExample();
        QuestionExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        return example;
    }

    @Override
    public List<Question> selectIndexQuestion() {
        List<Question> questionList = questionMapper.selectByExampleWithBLOBs(selectIndexQuestionExample());
        return questionList;
    }

    @Override
    public PageInfo selectIndexQuestionVOs(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Question> questionList = selectIndexQuestion();
        PageInfo pageInfo = new PageInfo(questionList);
        List<IndexQuestionVO> vos = models2vos(questionList);
        pageInfo.setList(vos);
        return pageInfo;
    }

    private QuestionExample selectIndexQuestionExample(){
        QuestionExample example = new QuestionExample();
        QuestionExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andDeletedEqualTo(false);
        return example;
    }


    /**
     * 查询出questionVOs，考虑屏蔽
     * @return
     */
    @Override
    public List<IndexQuestionVO> selectIndexQuestionVOs() {
        List<Question> questionList = questionMapper.selectByExampleWithBLOBs(selectIndexQuestionVOsExample());
        return models2vos(questionList);
    }
    //example语句
    private QuestionExample selectIndexQuestionVOsExample(){
        QuestionExample example = new QuestionExample();
        QuestionExample.Criteria criteria = example.createCriteria();
        criteria.andShieldedEqualTo(false);
        criteria.andDeletedEqualTo(false);
        return example;
    }
    @Override
    public List<Question> selectByUserId(Integer userId) {
        List<Question> questionList = questionMapper.selectByExampleWithBLOBs(selectByUserIdExample(userId));
        return questionList;
    }
    //example，查询条件userid，并且未被拉黑的Question
    private QuestionExample selectByUserIdExample(Integer userId){
        QuestionExample example = new QuestionExample();
        QuestionExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andShieldedEqualTo(false);
        criteria.andUserIdEqualTo(userId);
        return example;
    }
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
        List<IndexQuestionVO> vos = models2vos(questionList);
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



    //models to vos
    private List<IndexQuestionVO> models2vos(List<Question> models){
        List<IndexQuestionVO> vos = new ArrayList<>();
        List<Integer> userIds = new ArrayList<>();
        models.forEach(model ->{
            //去除重复的userId，防止后面的Map中key的重复
            if(!userIds.contains(model.getUserId())){
                userIds.add(model.getUserId());
            }
            IndexQuestionVO vo = new IndexQuestionVO();
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
