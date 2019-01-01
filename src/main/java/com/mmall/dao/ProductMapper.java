package com.mmall.dao;

import com.mmall.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    
    List<Product> selectList();

    List<Product> selectByNameAndId(@Param("name")String name,@Param("id")Integer id);

    /**
     * 根据商品名称和分类id列表查询
     */
    List<Product> selectByNameAndCategoryIds(@Param("name")String name,@Param("categoryIdList")List<Integer> categoryIdList);
}