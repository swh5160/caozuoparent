package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.PmsAttr;
import com.neuedu.entity.PmsCategory;
import com.neuedu.mapper.PmsAttrMapper;
import com.neuedu.service.PmsAttrService;
import com.neuedu.service.PmsCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品属性表 服务实现类
 * </p>
 *
 * @author 施文慧
 * @since 2022-09-28
 */
@Service
public class PmsAttrServiceImpl extends ServiceImpl<PmsAttrMapper, PmsAttr> implements PmsAttrService {
    @Resource
    PmsCategoryService pmsCategoryService;
    @Override
    public List<PmsAttr> list(Integer categoryId, Integer type) {
        List<PmsAttr> dataList = new ArrayList<>();
        this.getParentList(categoryId,type,dataList);
        QueryWrapper<PmsAttr> wrapper = new QueryWrapper<>();
        wrapper.eq("category_id",categoryId)
                .eq("type",type);
        dataList.addAll(this.list(wrapper));
        return dataList;
    }


    //查寻父类列表,查出来的数据放进集合当中，不用return
    private void getParentList(Integer categoryId,Integer type, List<PmsAttr> dataList){
        //1、查自己
        PmsCategory pmsCategory = pmsCategoryService.getById(categoryId);
        //2、查到时候通过自己查父类
        PmsCategory parentId = pmsCategoryService.getById(pmsCategory.getParentId());
        if(parentId != null){
            this.getParentList(parentId.getId(),type,dataList);
            QueryWrapper<PmsAttr> wrapper = new QueryWrapper<>();
            wrapper.eq("category_id",parentId.getId())
                    .eq("type",type);
            List<PmsAttr> list = this.list(wrapper);
            dataList.addAll(list);
        }

    }
    @Override
    public Boolean add(String name, Integer categoryId, Integer inputType, String inputList, Integer type) {
        PmsAttr pmsAttr = new PmsAttr(name,categoryId,inputType,inputList,type);
        return this.save(pmsAttr);
    }

    @Override
    public Boolean update(Integer id, String name, Integer inputType, String inputList) {
        PmsAttr pmsAttr = new PmsAttr(id,name,inputType,inputList);
        return this.updateById(pmsAttr);
    }
    //查属性
    @Override
    public List<PmsAttr> getActiveByCategoryIds(Long[] categoryIds) {
        QueryWrapper<PmsAttr> wrapper = new QueryWrapper<>();
        wrapper.in("category_id", categoryIds);
        return this.list(wrapper);
    }

}
