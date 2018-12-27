package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

/**
 * Created by dgx on 2018/12/27.
 */
public interface IUserService {

    ServerResponse<User> login(String username, String password);
}
