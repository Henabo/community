package org.zuel.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.community.mapper.UserMapper;
import org.zuel.community.model.User;
import org.zuel.community.model.UserExample;
import org.zuel.community.service.IUserService;
import org.zuel.community.util.ResponseUtil;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(User model) {
        model.setAddTime(System.currentTimeMillis());
        model.setAvatorUrl("https://i1.hdslb.com/bfs/face/c47dd5e63e179360daf0673a865c947cdc30a142.jpg_64x64.jpg");
        userMapper.insertSelective(model);
    }

    @Override
    public void delete(List<Integer> ids) {
        UserExample example = new UserExample();
        //去重查询
        example.setDistinct(true);
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        criteria.andDeletedEqualTo(false);
        userMapper.logicalDeleteByExample(example);
    }

    @Override
    public void update(User model) {
        model.setUpdateTime(System.currentTimeMillis());
        userMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public List<User> selete() {
        return null;
    }

    @Override
    public User selectByUserName(String userName) {
        UserExample example = new UserExample();
        example.setDistinct(true);
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        criteria.andDeletedEqualTo(false);
        List<User>  userList = userMapper.selectByExample(example);
        if(userList.size() == 0){
            return null;
        }
        return userList.get(0);
    }

    @Override
    public User login(String userName, String password){
        UserExample example = new UserExample();
        example.setDistinct(true);
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        criteria.andPasswordEqualTo(password);
        criteria.andDeletedEqualTo(false);
        List<User> userList = userMapper.selectByExample(example);
        if(userList.size() == 0){
            return null;
        }else {
            return userList.get(0);
        }
    }
}
