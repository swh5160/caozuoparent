package com.neuedu.service.impl;


import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.neuedu.entity.UmsFile;
import com.neuedu.service.FileService;
import com.neuedu.service.UmsFileService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 施子安
 * @create
 */
@Service
public class FileServiceImpl implements FileService {
    //minio连接
    @Value("${upload.url}")
    String url;
    @Value("${upload.username}")
    String username;
    @Value("${upload.password}")
    String password;
    @Resource
    UmsFileService umsFileService;
    @Override
    public String upload(String bucket, MultipartFile file) throws IOException, ServerException, InsufficientDataException, InternalException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, XmlParserException, ErrorResponseException {
        //获取文件md5 文件大小 后缀名
        String md5 = DigestUtils.md5DigestAsHex(file.getInputStream());
        long size = file.getSize();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        //通过md5 大小 后缀名查询数据库是否存在
        UmsFile umsFile = umsFileService.get(md5, size, extension);
        if (umsFile != null){
            return umsFile.getPath();
        }
        //如果没查询到，需要上传

        //根据地质用户名 密码 创建minio客户端
        MinioClient client = MinioClient.builder()
                .endpoint(url)
                .credentials(username,password)
                .build();
        //文件名字重命名
//        UUID uuid = UUID.randomUUID();
        StringBuilder builder = new StringBuilder();
        builder.append(NanoIdUtils.randomNanoId());
        builder.append(".");
        //后缀名工具,获取源文件后缀名获取扩展名
        builder.append(extension);
        //定义返回path
        String path = bucket + "/" +builder.toString();
        //设置上传2参数 桶名 类型 文件名
        PutObjectArgs objectArgs = PutObjectArgs.builder()
                .bucket(bucket)
                .contentType(file.getContentType())
                .object(builder.toString())
                .stream(file.getInputStream(),size,0)
                .build();
        //数据库存一份已上传文件信息
        umsFile = new UmsFile(md5,size,extension,path);
        umsFileService.save(umsFile);
        //上传文件
        client.putObject(objectArgs);
        return path;
    }
}
