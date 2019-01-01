package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import java.util.List;

/**
 * Created by dgx on 2018/12/29.
 */
public interface ICategoryService {

    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse setCategoryName(Integer categoryId, String categoryName);

    /**
     * 获取平级品类
     */
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    /**
     * 获取当前分类id及递归子节点categoryId
     */
    ServerResponse<List<Integer>> getCategoryAndDeepChildrenCategory(Integer categoryId);
}
