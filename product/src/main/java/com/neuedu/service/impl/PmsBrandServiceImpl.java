package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.PmsBrand;
import com.neuedu.mapper.PmsBrandMapper;
import com.neuedu.service.FileService;
import com.neuedu.service.PmsBrandService;
import io.minio.errors.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author 施文慧
 * @since 2022-09-27
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

    @Resource
    FileService fileService;
    String bucket = "logo";

    @Override
    @Cacheable(value = "pms",key = "'brands'")
    public List<PmsBrand> getAll() {
        //查询有效品牌
        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<>();
        wrapper.eq("active",1);
        return this.list(wrapper);
    }

    @Override
    public IPage<PmsBrand> list(int pageNo, int pageSize, String value) {
        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(value)){
            wrapper.like("name",value);
        }
        //倒序
        wrapper.orderByDesc("id");
        //调用分页查询带参数当前方法
        return this.page(new Page<>(pageNo,pageSize),wrapper);
    }

    @Override
    @CacheEvict(value ="pms",key = "'brands'")
    public Boolean add(String name, MultipartFile file, String description) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        PmsBrand pmsBrand = new PmsBrand(
                name,
                fileService.upload(bucket,file),
                description
        );
        return this.save(pmsBrand);
    }

    @Override
    @CacheEvict(value ="pms",key = "'brands'")
    public Boolean update(Integer id, String name, MultipartFile file, String description) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        PmsBrand pmsBrand = new PmsBrand(
                id,
                name,
                null,
                description
        );
        if (file != null && file.getSize() > 0){
            pmsBrand.setLogo(fileService.upload(bucket,file));
        }
        return this.updateById(pmsBrand);
    }

    @Override
    @CacheEvict(value ="pms",key = "'brands'")
    public Boolean del(Integer id, Boolean active) {
        PmsBrand pmsBrand = new PmsBrand(id,active);
        return this.updateById(pmsBrand);
    }

    @Override
    public String getNameById(Integer id) {
        return this.getById(id).getName();
    }

}
