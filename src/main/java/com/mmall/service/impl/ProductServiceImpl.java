package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.dao.ProductMapper;
import com.mmall.pojo.Category;
import com.mmall.pojo.Product;
import com.mmall.service.IProductService;
import com.mmall.util.PropertiesUtil;
import com.mmall.vo.ProductDetailVo;
import com.mmall.vo.ProductListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dgx on 2018/12/30.
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse saveOrUpdateProduct(Product product) {
        if (product == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }

        if (StringUtils.isNotBlank(product.getSubImages())){
            String[] subImagesArray = product.getSubImages().split(",");
            if (subImagesArray.length > 0){
                product.setMainImage(subImagesArray[0]);
            }
        }
        if (product.getId() == null){
            int rowCount = productMapper.insert(product);
            if (rowCount > 0){
                return ServerResponse.createBySuccessMessage("新增产品成功");
            }
            return ServerResponse.createByErrorMessage("新增产品失败");
        } else {
            int rowCount = productMapper.updateByPrimaryKey(product);
            if (rowCount > 0){
                return ServerResponse.createBySuccessMessage("更新产品成功");
            }
            return ServerResponse.createByErrorMessage("更新产品失败");
        }
    }

    @Override
    public ServerResponse setSaleStatus(Integer productId, Integer status) {
        if (productId == null || status == null){ //参数错误
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if (rowCount > 0){
            return ServerResponse.createBySuccessMessage("修改产品状态成功");
        }
        return ServerResponse.createByErrorMessage("修改产品状态失败");
    }

    @Override
    public ServerResponse<ProductDetailVo> manageProductDetail(Integer productId) {
        if (productId == null){ //参数错误
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }

        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null){
            return ServerResponse.createByErrorMessage("产品已下架或删除");
        }
//        vo对象--value object（本业务）
//        pojo->bo(business object)->vo(view object)（复杂业务时）
        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product,productDetailVo); //拷贝类

        productDetailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));
        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        if (category == null){
            productDetailVo.setParentCategoryId(0); //默认根节点
        } else {
            productDetailVo.setParentCategoryId(category.getParentId());
        }
        //createTime
        //updateTime 使用默认Date时间戳类型
        return ServerResponse.createBySuccess(productDetailVo);
    }

    @Override
    public ServerResponse<PageInfo> getProductList(int pageNum, int pageSize) {
        // startPage--开始
        // 填充自己的sql查询逻辑
        // pageHelper--收尾
        PageHelper.startPage(pageNum,pageSize);

        List<Product> productList = productMapper.selectList();

        List<ProductListVo> productListVoList = Lists.newArrayList();
        for (Product productItem : productList){
            ProductListVo productListVo = new ProductListVo();
            BeanUtils.copyProperties(productItem,productListVo); //拷贝类

            productListVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));
            productListVoList.add(productListVo);
        }

        PageInfo pageResult = new PageInfo(productList);
        pageResult.setList(productListVoList);

        return ServerResponse.createBySuccess(pageResult); //将分页插件的PageInfo返回
    }

    @Override
    public ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        // ~~~~~~
        return null;
    }
}
