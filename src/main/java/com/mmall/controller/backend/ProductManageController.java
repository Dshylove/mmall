package com.mmall.controller.backend;

import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.IFileService;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import com.mmall.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

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
    @Autowired
    private IFileService iFileService;

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
    public ServerResponse searchProduct(@RequestParam(value = "productName",required = false)String productName,
                                        @RequestParam(value = "productId",required = false)Integer productId,
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
        return iProductService.searchProduct(productName, productId, pageNum, pageSize);
    }

    @RequestMapping(value = "upload.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse upload(@RequestParam(value = "upload_file",required = false)MultipartFile file,
                                 HttpServletRequest request,HttpSession session){
        // 检查是否登录 todo: 使用拦截器统一判断
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，需要强制登录，status=10");
        }
        // 检查是否为管理员 todo: 使用拦截器统一判断
        if (!iUserService.checkAdminRole(currentUser).isSuccess()){
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }

        // 获取tomcat工程绝对路径 + upload目录
        String path = request.getSession().getServletContext().getRealPath("upload");
        // path: D:\\Workspaces\\IdeaProjects\\mmall\\target\\mmall\\upload
//        String path = "D:/upload";
        // 上传文件
        String targetFileName = iFileService.upload(file,path);
        // 拼接图片url地址
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("uri",targetFileName);
        fileMap.put("url",url);
        return ServerResponse.createBySuccess(fileMap);
    }

    /**
     * 富文本上传
     * @param file
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "richtext_img_upload.do",method = RequestMethod.POST)
    @ResponseBody
    public Map richtextImgUpload(@RequestParam(value = "upload_file",required = false)MultipartFile file,
                                 HttpServletRequest request, HttpServletResponse response, HttpSession session){
        // 富文本中对于返回值有要求，按照simditor的要求进行返回
//        {
//            "success": true/false,
//                "msg": "error message", # optional
//            "file_path": "[real file path]"
//        }
        Map resultMap = Maps.newHashMap();

        // 检查是否登录
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            resultMap.put("success",false);
            resultMap.put("msg","未登录，请登录管理员");
            return resultMap;
        }
        // 检查是否为管理员
        if (!iUserService.checkAdminRole(currentUser).isSuccess()){
            resultMap.put("success",false);
            resultMap.put("msg","无权限操作，需要管理员权限");
            return resultMap;
        }

        // 获取tomcat工程绝对路径 + upload目录
        String path = request.getSession().getServletContext().getRealPath("upload");
        // 上传文件
        String targetFileName = iFileService.upload(file,path);
        if (StringUtils.isBlank(targetFileName)){
            resultMap.put("success",false);
            resultMap.put("msg","上传失败");
            return resultMap;
        }
        // 拼接图片url地址
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
        resultMap.put("success",true);
        resultMap.put("msg","上传成功");
        resultMap.put("file_path",url);
        // 设置约定的响应头
        response.addHeader("Access-Control-Allow-Headers","X-File-Name");
        return resultMap;
    }
}
