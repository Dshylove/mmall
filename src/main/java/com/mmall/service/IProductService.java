package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVo;

/**
 * Created by dgx on 2018/12/30.
 */
public interface IProductService {

    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse setSaleStatus(Integer productId,Integer status);

    /**
     * 后台，获取产品detail
     */
    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    /**
     * 获取所有产品列表
     */
    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    /**
     * 条件搜索产品列表
     */
    ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);

    /**
     * 门户，获取产品detail
     */
    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    /**
     * 门户，产品搜索
     */
    ServerResponse<PageInfo> getProductByKeywordCategoryId(Integer categoryId, String keyword, int pageNum, int pageSize, String orderBy);
}
