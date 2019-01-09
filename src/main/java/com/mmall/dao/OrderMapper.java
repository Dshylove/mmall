package com.mmall.dao;

import com.mmall.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);


    Order selectByUserIdAndOrderNo(@Param("userId")Integer userId, @Param("orderNo")Long orderNo);

    Order selectByOrderNo(@Param("orderNo")Long orderNo);

    /**
     * 查询用户订单列表，或所有用户订单列表
     * @param userId 参数值为null时，查询所有用户订单
     * @return
     */
    List<Order> selectByUserId(@Param("userId")Integer userId);
}