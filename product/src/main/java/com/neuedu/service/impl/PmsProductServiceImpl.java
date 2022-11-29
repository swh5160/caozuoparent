package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.PmsProduct;
import com.neuedu.mapper.PmsProductMapper;
import com.neuedu.service.PmsProductService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author 施子安
 * @since 2022-09-28
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {

    @Override
    public IPage<PmsProduct> list(Integer pageNo, Integer pageSize, String value) {
        return this.page(new Page<>(pageNo,pageSize));
    }
}
