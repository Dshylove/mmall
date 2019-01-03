package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVo;

/**
 * Created by dgx on 2019/1/2.
 */
public interface ICartService {

    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    /**
     * 全选或取消全选
     */
    ServerResponse<CartVo> selectAllOrUnSelectAll(Integer userId, Integer checked);

    /**
     * 单独选或取消单独选
     */
    ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId, Integer checked);

    /**
     * 获取购物车里的产品数量
     */
    ServerResponse<Integer> getCartProductCount(Integer userId);
}
