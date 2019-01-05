package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by dgx on 2019/1/6.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @RequestMapping(value = "pay.do")
    @ResponseBody
    public ServerResponse pay(@RequestParam("orderNo")Long orderNo, HttpSession session, HttpServletRequest request){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        // 获取tomcat工程绝对路径 + upload目录
        // path: D:/Workspaces/IdeaProjects/mmall/target/mmall/upload
        String path = request.getSession().getServletContext().getRealPath("upload");
        return iOrderService.pay(currentUser.getId(),orderNo,path);
    }


}
