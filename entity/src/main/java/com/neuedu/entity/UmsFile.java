package com.neuedu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author 施文慧
 * @since 2022-09-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UmsFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文件md5
     */
    private String md5;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 后缀名称
     */
    private String extension;

    /**
     * 文件路径
     */
    private String path;

    public UmsFile(String md5, Long size, String extension, String path) {
        this.md5 = md5;
        this.size = size;
        this.extension = extension;
        this.path = path;
    }
}
