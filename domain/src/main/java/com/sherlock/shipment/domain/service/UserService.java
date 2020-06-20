package com.sherlock.shipment.domain.service;

import com.sherlock.model.vo.UserVo;

/**
 * @author Sherlock
 * @copyright freeman
 * @since 2020/6/15 15:46
 */
public interface UserService {

    /**
     *  用户登陆
     * @param name 用户名
     * @param password 密码
     * @return 返回登陆用户
     */
    UserVo login(String name, String password);
}
