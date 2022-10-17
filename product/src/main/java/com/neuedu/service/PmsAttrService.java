package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.PmsAttr;

import java.util.List;

/**
 * <p>
 * 商品属性表 服务类
 * </p>
 *
 * @author 施文慧
 * @since 2022-09-28
 */
public interface PmsAttrService extends IService<PmsAttr> {

    List<PmsAttr> list(Integer categoryId, Integer type);
    //分类
    List<PmsAttr> getActiveByCategoryIds(Long[] categoryIds);
    Boolean add(String name , Integer categoryId, Integer inputType, String inputList , Integer type);
    Boolean update(Integer id,String name ,Integer inputType,String inputList);
}
