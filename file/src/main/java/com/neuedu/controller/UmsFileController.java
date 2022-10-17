package com.neuedu.controller;


import com.neuedu.common.ResultJson;
import com.neuedu.service.FileService;
import io.minio.errors.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 文件表 前端控制器
 * </p>
 *
 * @author 施文慧
 * @since 2022-09-27
 */
@RestController
@RequestMapping("/umsFile")
public class UmsFileController {
    @Resource
    FileService fileService;
    @PostMapping("/upload")
    ResultJson upload(String bucket, MultipartFile file) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InternalException {
        return  ResultJson.success(fileService.upload(bucket,file));
    }
}
