package com.neuedu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.UmsUser;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author shiwenhui
 * @since 2022-09-18
 */
public interface UmsUserService extends IService<UmsUser> {
    IPage<UmsUser> list(int pageNo, int pageSize, String value);

    Boolean add(String name, String phone, String email, MultipartFile file, String password) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    Boolean update(Integer id,String name, String phone, String email, MultipartFile file) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

}
