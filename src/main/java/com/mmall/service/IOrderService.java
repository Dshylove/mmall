package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.vo.OrderVo;

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



    ServerResponse createOrder(Integer userId, Integer shippingId);

    ServerResponse cancel(Integer userId, Long orderNo);

    /**
     * 获取购物车中已选中的商品详情
     */
    ServerResponse getOrderCartProduct(Integer userId);

    ServerResponse<OrderVo> detail(Integer userId, Long orderNo);

    /**
     * 查询订单列表，分页（前台、后台共用）
     * @param userId 当后台管理接口调用时，userId值需置为null
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);

    /**
     * 查询订单详细（后台）
     * @param orderNo
     * @return
     */
    ServerResponse<OrderVo> manageDetail(Long orderNo);

    /**
     * 根据订单号搜索，分页（后台）
     * @param orderNo
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> manageSearch(Long orderNo, int pageNum, int pageSize);


    /**
     * 订单发货（后台）
     * @param orderNo
     * @return
     */
    ServerResponse manageSendGoods(Long orderNo);
}
