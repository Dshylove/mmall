package com.mmall.dao;

import com.mmall.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);


    Cart selectByUserIdProductId(@Param("userId")Integer userId, @Param("productId")Integer productId);

    List<Cart> selectByUserId(@Param("userId")Integer userId);

    int deleteByUserIdProductIds(@Param("userId")Integer userId, @Param("productIdList")List<String> productIdList);

    int checkedOrUnCheckedAllByUserId(@Param("userId")Integer userId, @Param("checked")Integer checked);

    int checkedOrUnCheckedByUserIdAndProductId(@Param("userId")Integer userId, @Param("productId")Integer productId,
                                               @Param("checked")Integer checked);

    int selectCartProductCount(@Param("userId")Integer userId);


    List<Cart> selectCheckedByUserId(@Param("userId")Integer userId);
}