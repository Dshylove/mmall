package com.mmall.service;

import com.mmall.common.ServerResponse;

/**
 * Created by dgx on 2019/1/6.
 */
public interface IOrderService {

    ServerResponse pay(Integer userId, Long orderNo, String path);
}
