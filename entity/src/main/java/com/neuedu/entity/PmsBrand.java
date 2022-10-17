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
 * 品牌表
 * </p>
 *
 * @author 施文慧
 * @since 2022-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PmsBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌logo
     */
    private String logo;

    /**
     * 品牌故事
     */
    private String description;

    /**
     * 状态
     */
    private Boolean active;


    public PmsBrand(String name, String logo, String description) {
        this.name = name;
        this.logo = logo;
        this.description = description;
    }

    public PmsBrand(Integer id, String name, String logo, String description) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.description = description;
    }

    public PmsBrand(Integer id, Boolean active) {
        this.id = id;
        this.active = active;
    }
}
