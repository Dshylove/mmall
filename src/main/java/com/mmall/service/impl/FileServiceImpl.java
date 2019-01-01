package com.mmall.service.impl;

import com.mmall.service.IFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by dgx on 2018/12/31.
 */
@Service
public class FileServiceImpl implements IFileService{

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        // 获取扩展名，不包括(.)例如(jpg)
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 设置文件名
        String uploadFileName = UUID.randomUUID().toString().replace("-","") + "." + fileExtensionName;
        logger.info("开始文件");
        // 创建目录
        File fileDir = new File(path);
        if (!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);
            // 文件上传成功
            // todo 将targetFile上传到FTP服务器
            // todo 上传完删除upload下的文件
        } catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }
}
