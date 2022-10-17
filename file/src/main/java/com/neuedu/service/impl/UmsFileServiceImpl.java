package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.entity.UmsFile;
import com.neuedu.mapper.UmsFileMapper;
import com.neuedu.service.UmsFileService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author 施文慧
 * @since 2022-09-27
 */
@Service
public class UmsFileServiceImpl extends ServiceImpl<UmsFileMapper, UmsFile> implements UmsFileService {

    @Override
    public UmsFile get(String md5, long size, String extension) {
        //条件查询
        QueryWrapper<UmsFile> wrapper = new QueryWrapper<>();
        wrapper.eq("md5",md5)
                .eq("size",size)
                .eq("extension",extension);
        return this.getOne(wrapper);
    }
}
