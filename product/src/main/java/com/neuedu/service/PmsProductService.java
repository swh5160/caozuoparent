package com.neuedu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.PmsProduct;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author 施子安
 * @since 2022-09-28
 */
public interface PmsProductService extends IService<PmsProduct> {
    IPage<PmsProduct> list(Integer pageNo,Integer pageSize,String value);
}
