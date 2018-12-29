package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by dgx on 2018/12/29.
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(value = "add_category.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, @RequestParam("categoryName")String categoryName,
                                      @RequestParam(value = "parentId",defaultValue = "0")Integer parentId){
        // 检查是否登录 todo: 使用拦截器统一判断
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，需要强制登录，status=10");
        }
        // 检查是否为管理员 todo: 使用拦截器统一判断
        if (!iUserService.checkAdminRole(currentUser).isSuccess()){
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
        // 处理分类的逻辑
        return iCategoryService.addCategory(categoryName, parentId);
    }

    @RequestMapping(value = "set_category_name.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setCategoryName(@RequestParam("categoryId")Integer categoryId,
                                          @RequestParam("categoryName")String categoryName, HttpSession session){
        // 检查是否登录 todo: 使用拦截器统一判断
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，需要强制登录，status=10");
        }
        // 检查是否为管理员 todo: 使用拦截器统一判断
        if (!iUserService.checkAdminRole(currentUser).isSuccess()){
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }

        return iCategoryService.setCategoryName(categoryId, categoryName);
    }

    @RequestMapping(value = "get_category.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(@RequestParam(value = "categoryId",defaultValue = "0")Integer categoryId,
                                                      HttpSession session){
        // 检查是否登录 todo: 使用拦截器统一判断
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，需要强制登录，status=10");
        }
        // 检查是否为管理员 todo: 使用拦截器统一判断
        if (!iUserService.checkAdminRole(currentUser).isSuccess()){
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
        // 查询子节点的category信息，并且不递归，保持平级
        return iCategoryService.getChildrenParallelCategory(categoryId);
    }

    @RequestMapping(value = "get_deep_category.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(@RequestParam(value = "categoryId",defaultValue = "0")Integer categoryId,
                                                      HttpSession session){
        // 检查是否登录 todo: 使用拦截器统一判断
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，需要强制登录，status=10");
        }
        // 检查是否为管理员 todo: 使用拦截器统一判断
        if (!iUserService.checkAdminRole(currentUser).isSuccess()){
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
        // 查询当前节点的id和递归子节点的id
        // 0->100->10000
        return iCategoryService.getCategoryAndDeepChildrenCategory(categoryId);
    }
}
