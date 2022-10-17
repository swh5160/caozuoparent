package com.neuedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.entity.UmsFile;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author 施文慧
 * @since 2022-09-27
 */
public interface UmsFileService extends IService<UmsFile> {
    UmsFile get(String md5, long size, String extension);
}
