package com.mmall.vo;

import com.mmall.pojo.Product;

/**
 * Created by dgx on 2018/12/30.
 */
public class ProductDetailVo extends Product {

    private String imageHost;

    private Integer parentCategoryId;

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}
