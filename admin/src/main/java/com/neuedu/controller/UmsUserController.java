package com.neuedu.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.neuedu.common.ResultJson;
import com.neuedu.core.SentinelUtil;
import com.neuedu.entity.UmsUser;
import com.neuedu.service.UmsUserService;
import io.minio.errors.*;
import org.springframework.web.bind.annotation.GetMapping;
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
 * 用户表 前端控制器
 * </p>
 *
 * @author shiwenhui
 * @since 2022-09-18
 */
@RestController
@RequestMapping("/umsUser")
public class UmsUserController {
    @Resource
    UmsUserService umsUserService;

    @GetMapping("/list")
    @SentinelResource(value = "user_list",blockHandlerClass = {SentinelUtil.class},blockHandler = "handlerException")
    ResultJson list(Integer pageNo, Integer pageSize, String value){
        return  ResultJson.success(umsUserService.list(pageNo,pageSize,value));
    }
    @PostMapping("/add")
    ResultJson add(String name, String phone, String email, MultipartFile file, String password) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        return ResultJson.success(umsUserService.add(name,phone,email,file,password),"添加成功");
    }
    @GetMapping("/getOne")
    ResultJson getOne(Integer id){
        return ResultJson.success(umsUserService.getById(id));
    }
    @PostMapping("/update")
    ResultJson update(Integer id, String name, String phone, String email, MultipartFile file) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        return ResultJson.success(umsUserService.update(id,name,phone,email,file),"修改用户成功");
    }
    @PostMapping("/del")
    ResultJson del(Integer id, Boolean active){
        UmsUser umsUser = new UmsUser();
        umsUser.setId(id);
        umsUser.setActive(active);
        return ResultJson.success(umsUserService.updateById(umsUser),active ? "用户恢复成功" : "用户删除成功");
    }
}
