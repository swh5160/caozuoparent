package com.neuedu.controller;


import com.neuedu.common.ResultJson;
import com.neuedu.config.MyException;
import com.neuedu.service.PmsCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author 施文慧
 * @since 2022-09-27
 */
@RestController
@RequestMapping("/pmsCategory")
public class PmsCategoryController {
    @Resource
    PmsCategoryService pmsCategoryService;
    @GetMapping("/list")
    ResultJson list(){
        return  ResultJson.success(pmsCategoryService.get());
    }
    @PostMapping("/add")
    ResultJson add(String name, Integer parentId, Integer level){
        return ResultJson.success(pmsCategoryService.add(name,parentId,level),"添加分类成功");
    }
    @PostMapping("/update")
    ResultJson update(Integer id , String name){
        return ResultJson.success(pmsCategoryService.update(id,name),"修改分类成功");
    }
    @GetMapping("/getOne")
    ResultJson getOne(Integer id){
        return ResultJson.success(pmsCategoryService.getById(id));
    }

    @PostMapping("/del")
    ResultJson del(Integer id, Boolean active) throws MyException {
        return  ResultJson.success(pmsCategoryService.del(id,active),active ? "恢复分类成功" : "删除分类成功");    }
}
