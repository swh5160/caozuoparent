package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.config.MyException;
import com.neuedu.entity.PmsCategory;

import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author 施文慧
 * @since 2022-09-27
 */
public interface PmsCategoryService extends IService<PmsCategory> {
    //查层级，递归
    List<PmsCategory> get();
    //查找有有效数据
    List<PmsCategory> getActive();

    Boolean add(String name,Integer parentId,Integer level);
    Boolean update(Integer id,String name);
    Boolean del(Integer id,Boolean active) throws MyException;
    String getNameByIds(Integer[] categoryIds);
}
