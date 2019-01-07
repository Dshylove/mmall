package com.mmall.service;

import com.mmall.common.ServerResponse;

import java.util.Map;

/**
 * Created by dgx on 2019/1/6.
 */
public interface IOrderService {

    /**
     * 支付
     */
    ServerResponse pay(Integer userId, Long orderNo, String path);

    /**
     * 支付宝回调
     */
    ServerResponse alipayCallback(Map<String,String> params);

    /**
     * 查询订单支付状态
     */
    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);
}
