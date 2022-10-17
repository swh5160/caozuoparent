package com.neuedu.controller;


import com.neuedu.common.ResultJson;
import com.neuedu.service.PmsBrandService;
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
 * 品牌表 前端控制器
 * </p>
 *
 * @author 施文慧
 * @since 2022-09-27
 */
@RestController
@RequestMapping("/pmsBrand")
public class PmsBrandController {
    @Resource
    PmsBrandService pmsBrandService;
    @GetMapping("/list")
    ResultJson list (Integer pageNo, Integer pageSize, String value){
        return  ResultJson.success(pmsBrandService.list(pageNo,pageSize,value));
    }
    @PostMapping("/add")
    ResultJson add(String name , MultipartFile file, String description) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        return ResultJson.success(pmsBrandService.add(name,file,description),"添加品牌成功");
    }
    @GetMapping("/getOne")
    ResultJson getOne(Integer id){
        return  ResultJson.success(pmsBrandService.getById(id));
    }
    @PostMapping("/update")
    ResultJson update(Integer id, String name , MultipartFile file, String description) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        return ResultJson.success(pmsBrandService.update(id,name,file,description),"修改品牌成功");
    }
    @PostMapping("/del")
    ResultJson del(Integer id, Boolean active){
        return ResultJson.success(pmsBrandService.del(id,active), active ? "恢复品牌成功" : "删除品牌成功");
    }
}
