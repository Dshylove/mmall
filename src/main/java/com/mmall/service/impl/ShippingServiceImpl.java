package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.ShippingMapper;
import com.mmall.pojo.Shipping;
import com.mmall.service.IShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by dgx on 2019/1/4.
 */
@Service
public class ShippingServiceImpl implements IShippingService{

    private Logger logger = LoggerFactory.getLogger(ShippingServiceImpl.class);

    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    public ServerResponse add(Integer userId, Shipping shipping) {
        // 设置userId，防止横向越权
        shipping.setUserId(userId);
        int rowCount = 0;
        try {
            rowCount = shippingMapper.insert(shipping);
        } catch (Exception e) {
            logger.error("非法参数，插入地址SQL执行失败",e); //捕获一下插入的SQL错误
        }
        if (rowCount > 0){
            Map result = Maps.newHashMap();
            result.put("shippingId",shipping.getId());
            return ServerResponse.createBySuccess("新建地址成功",result);
        }
        return ServerResponse.createByErrorMessage("新建地址失败");
    }

    @Override
    public ServerResponse del(Integer userId, Integer shippingId) {
        if (shippingId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        // shippingId和userId绑定删除条件，防止横向越权
        int resultCount = shippingMapper.deleteByPrimaryKeyAndUserId(shippingId,userId);
        if (resultCount > 0){
            return ServerResponse.createBySuccessMessage("删除地址成功");
        }
        return ServerResponse.createByErrorMessage("删除地址失败");
    }

    @Override
    public ServerResponse update(Integer userId, Shipping shipping) {
        // 设置userId，防止横向越权
        shipping.setUserId(userId);
        // shippingId和userId绑定更新条件，防止横向越权
        int rowCount = shippingMapper.updateByPrimaryKeyAndUserId(shipping);
        if (rowCount > 0){
            return ServerResponse.createBySuccessMessage("修改地址成功");
        }
        return ServerResponse.createByErrorMessage("修改地址失败");
    }

    @Override
    public ServerResponse select(Integer userId, Integer shippingId) {
        if (shippingId == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        // shippingId和userId绑定查询条件，防止横向越权
        Shipping shipping = shippingMapper.selectByPrimaryKeyAndUserId(shippingId,userId);
        if (shipping != null){
            return ServerResponse.createBySuccess(shipping);
        }
        return ServerResponse.createByErrorMessage("没有查询到该地址");
    }

    @Override
    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(shippingList);

        return ServerResponse.createBySuccess(pageInfo);
    }
}
