package com.neuedu.controller;


import com.neuedu.common.ResultJson;
import com.neuedu.service.PmsAttrService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 商品属性表 前端控制器
 * </p>
 *
 * @author 施文慧
 * @since 2022-09-28
 */
@RestController
@RequestMapping("/pmsAttr")
public class PmsAttrController {
    @Resource
    PmsAttrService pmsAttrService;
    @GetMapping("/list")
    ResultJson list(Integer categoryId, Integer type){
        return ResultJson.success(pmsAttrService.list(categoryId,type));
    }
    @PostMapping("/add")
    ResultJson add(String name , Integer categoryId, Integer inputType, String inputList , Integer type){
        return ResultJson.success(pmsAttrService.add(name,categoryId,inputType,inputList, type),"添加属性成功");
    }
    @PostMapping("/update")
    ResultJson update(Integer id, String name , Integer inputType, String inputList){
        return ResultJson.success(pmsAttrService.update(id,name,inputType,inputList),"修改属性成功");
    }
    @GetMapping("/getOne")
    ResultJson getOne(Integer id){
        return ResultJson.success(pmsAttrService.getById(id));
    }
    @PostMapping("/del")
    ResultJson del(Integer id){
        return ResultJson.success(pmsAttrService.removeById(id));
    }
}
