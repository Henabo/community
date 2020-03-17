package org.zuel.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuel.community.mapper.RegisterMapper;
import org.zuel.community.model.Register;
import org.zuel.community.model.RegisterExample;
import org.zuel.community.service.IRegisterService;
import org.zuel.community.util.ResponseUtil;

import java.util.List;

@Service
public class RegisterService implements IRegisterService {
    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public Object add(Register model) {
        model.setAddTime(System.currentTimeMillis());
        registerMapper.insertSelective(model);
        model = registerMapper.selectByPrimaryKey(model.getId());
        return ResponseUtil.ok(model);
    }

    @Override
    public Object delete(List<Integer> ids) {
        RegisterExample example = new RegisterExample();
        example.setDistinct(true);
        RegisterExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        criteria.andDeletedEqualTo(false);
        registerMapper.logicalDeleteByExample(example);
        return null;
    }

    @Override
    public Object update(Register model) {
        model.setUpdateTime(System.currentTimeMillis());
        registerMapper.updateByPrimaryKeySelective(model);
        model = registerMapper.selectByPrimaryKey(model.getId());
        return ResponseUtil.ok(model);
    }

    @Override
    public Object select() {
        return null;
    }

    @Override
    public Object selectNotDeal() {
        RegisterExample example = new RegisterExample();
        example.setDistinct(true);
        RegisterExample.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(0);
        criteria.andDeletedEqualTo(false);
        List<Register> registerList = registerMapper.selectByExample(example);
        return ResponseUtil.ok(registerList);
    }
}
