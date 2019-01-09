package com.mmall.controller.backend;

import com.github.pagehelper.PageInfo;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IOrderService;
import com.mmall.service.IUserService;
import com.mmall.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by dgx on 2019/1/9.
 */
@Controller
@RequestMapping("/manage/order")
public class OrderManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IOrderService iOrderService;

    @RequestMapping(value = "list.do")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
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
        return iOrderService.list(null,pageNum,pageSize);
    }

    @RequestMapping(value = "detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> detail(@RequestParam("orderNo")Long orderNo, HttpSession session){
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
        return iOrderService.manageDetail(orderNo);
    }

    @RequestMapping(value = "search.do")
    @ResponseBody
    public ServerResponse<PageInfo> search(@RequestParam("orderNo")Long orderNo, HttpSession session,
                                          @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                          @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
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
        return iOrderService.manageSearch(orderNo, pageNum, pageSize);
    }

    @RequestMapping(value = "send_goods.do")
    @ResponseBody
    public ServerResponse sendGoods(@RequestParam("orderNo")Long orderNo, HttpSession session){
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
        return iOrderService.manageSendGoods(orderNo);
    }
}
