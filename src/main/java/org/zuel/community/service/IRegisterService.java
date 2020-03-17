package org.zuel.community.service;

import org.zuel.community.model.Register;

import java.util.List;

public interface IRegisterService {
    Object add(Register model);
    Object delete(List<Integer> ids);
    Object update(Register model);
    Object select();

    /**
     * 查询没处理的注册申请
     * @return
     */
    Object selectNotDeal();
}
