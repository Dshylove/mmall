package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by dgx on 2018/12/30.
 */
@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IProductService iProductService;

    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse saveProduct(Product product, HttpSession session){
        // 检查是否登录 todo: 使用拦截器统一判断
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，需要强制登录，status=10");
        }
        // 检查是否为管理员 todo: 使用拦截器统一判断
        if (!iUserService.checkAdminRole(currentUser).isSuccess()){
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }

        // 处理业务逻辑
        return iProductService.saveOrUpdateProduct(product);
    }

    @RequestMapping(value = "set_sale_status.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setSaleStatus(@RequestParam("productId")Integer productId,
                                        @RequestParam("status")Integer status, HttpSession session){
        // 检查是否登录 todo: 使用拦截器统一判断
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，需要强制登录，status=10");
        }
        // 检查是否为管理员 todo: 使用拦截器统一判断
        if (!iUserService.checkAdminRole(currentUser).isSuccess()){
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }

        // 处理业务逻辑
        return iProductService.setSaleStatus(productId, status);
    }

    @RequestMapping(value = "detail.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDetail(@RequestParam("productId")Integer productId, HttpSession session){
        // 检查是否登录 todo: 使用拦截器统一判断
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，需要强制登录，status=10");
        }
        // 检查是否为管理员 todo: 使用拦截器统一判断
        if (!iUserService.checkAdminRole(currentUser).isSuccess()){
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }

        // 处理业务逻辑
        return iProductService.manageProductDetail(productId);
    }

    @RequestMapping(value = "list.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getList(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, HttpSession session){
        // 检查是否登录 todo: 使用拦截器统一判断
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，需要强制登录，status=10");
        }
        // 检查是否为管理员 todo: 使用拦截器统一判断
        if (!iUserService.checkAdminRole(currentUser).isSuccess()){
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }

        // 处理业务逻辑
        return iProductService.getProductList(pageNum, pageSize);
    }

    @RequestMapping(value = "search.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse searchProduct(@RequestParam("productName")String productName,
                                        @RequestParam("productId")Integer productId,
                                        @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, HttpSession session){
        // 检查是否登录 todo: 使用拦截器统一判断
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，需要强制登录，status=10");
        }
        // 检查是否为管理员 todo: 使用拦截器统一判断
        if (!iUserService.checkAdminRole(currentUser).isSuccess()){
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }

        // 处理业务逻辑
        return null;
    }
}
