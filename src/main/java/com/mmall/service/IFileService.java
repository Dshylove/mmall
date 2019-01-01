package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by dgx on 2018/12/31.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
