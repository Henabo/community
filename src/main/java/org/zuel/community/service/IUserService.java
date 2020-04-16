package org.zuel.community.service;

import org.zuel.community.model.User;

import java.util.List;

public interface IUserService {
    /**
     * 添加用户
     * @param model
     * @return
     */
    void add(User model);

    /**
     * 删除用户，List<ids>，List类型可以删除一条，也可以删除多条
     * @param ids
     * @return
     */
    void delete(List<Integer> ids);

    /**
     * 修改用户信息
     * @param model
     * @return
     */
    void update(User model);

    /**
     * 查询用户
     * @return
     */
    List<User> selete();

    /**
     * 通过用户名查询
     * @param userName
     * @return
     */
    User selectByUserName(String userName);

    /**
     * 登陆
     * @param userName
     * @param password
     * @return
     */
    User login(String userName, String password);
}
