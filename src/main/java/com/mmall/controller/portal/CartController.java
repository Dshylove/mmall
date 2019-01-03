package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICartService;
import com.mmall.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by dgx on 2019/1/2.
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService iCartService;

    @RequestMapping(value = "list.do")
    @ResponseBody
    public ServerResponse<CartVo> list(HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.list(currentUser.getId());
    }

    @RequestMapping(value = "add.do")
    @ResponseBody
    public ServerResponse<CartVo> add(@RequestParam("productId")Integer productId,
                                      @RequestParam("count")Integer count, HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.add(currentUser.getId(),productId,count);
    }

    @RequestMapping(value = "update.do")
    @ResponseBody
    public ServerResponse<CartVo> update(@RequestParam("productId")Integer productId,
                                      @RequestParam("count")Integer count, HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.update(currentUser.getId(),productId,count);
    }

    @RequestMapping(value = "delete_product.do")
    @ResponseBody
    public ServerResponse<CartVo> deleteProduct(@RequestParam("productIds")String productIds, HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.deleteProduct(currentUser.getId(),productIds);
    }

    /**
     * 全选
     */
    @RequestMapping(value = "select_all.do")
    @ResponseBody
    public ServerResponse<CartVo> selectAll(HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectAllOrUnSelectAll(currentUser.getId(),Const.Cart.CHECKED);
    }

    /**
     * 取消全选
     */
    @RequestMapping(value = "un_select_all.do")
    @ResponseBody
    public ServerResponse<CartVo> unSelectAll(HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectAllOrUnSelectAll(currentUser.getId(),Const.Cart.UN_CHECKED);
    }

    /**
     * 单独选
     */
    @RequestMapping(value = "select.do")
    @ResponseBody
    public ServerResponse<CartVo> select(@RequestParam("productId")Integer productId, HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectOrUnSelect(currentUser.getId(),productId,Const.Cart.CHECKED);
    }

    /**
     * 取消单独选
     */
    @RequestMapping(value = "un_select.do")
    @ResponseBody
    public ServerResponse<CartVo> unSelect(@RequestParam("productId")Integer productId, HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectOrUnSelect(currentUser.getId(),productId,Const.Cart.UN_CHECKED);
    }

    /**
     * 获取购物车里的产品数量
     */
    @RequestMapping(value = "get_cart_product_count.do")
    @ResponseBody
    public ServerResponse<Integer> getCartProductCount(HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createBySuccess(0);
        }
        return iCartService.getCartProductCount(currentUser.getId());
    }

}
