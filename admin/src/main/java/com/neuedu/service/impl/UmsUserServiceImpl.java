package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.UmsUser;
import com.neuedu.mapper.UmsUserMapper;
import com.neuedu.service.FileService;
import com.neuedu.service.UmsUserService;
import io.minio.errors.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author shiwenhui
 * @since 2022-09-18
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements UmsUserService {
    //注入密码加密组件
    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    FileService fileService;

    String bucket = "icon";
    @Override
    public IPage<UmsUser> list(int pageNo, int pageSize, String value) {
        //实例化接收参数实现类
        QueryWrapper wrapper = new QueryWrapper();
        //判断value是否为空
        if (StringUtils.isNotBlank(value)){
            //模糊查询对应数据库属性
            wrapper.like("name",value);
        }
        //倒序
        wrapper.orderByDesc("id");
        //调用分页查询带参数当前方法
        return this.page(new Page<>(pageNo,pageSize),wrapper);
    }

    @Override
    public Boolean add(String name, String phone, String email, MultipartFile file, String password) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {

        //bCryptPasswordEncoder.encode调用加密方法
        UmsUser umsUser = new UmsUser(
                name,
                phone,
                email,
                fileService.upload("icon",file),
                bCryptPasswordEncoder.encode(password)
        );
        return this.save(umsUser);
    }

    @Override
    public Boolean update(Integer id, String name, String phone, String email, MultipartFile file) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        UmsUser umsUser = new UmsUser(id,name,phone,email);
        if (file != null && file.getSize() > 0){
            umsUser.setIcon(fileService.upload("icon",file));
        }
        return this.updateById(umsUser);
    }
}
