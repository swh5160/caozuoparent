package com.neuedu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.PmsBrand;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author 施文慧
 * @since 2022-09-27
 */
public interface PmsBrandService extends IService<PmsBrand> {
    List<PmsBrand> getAll();

    IPage<PmsBrand> list(int pageNo, int pageSize, String value);

    Boolean add(String name, MultipartFile file, String description) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    Boolean update(Integer id, String name, MultipartFile file, String description) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    Boolean del(Integer id,Boolean active);

    String getNameById(Integer id);
}
