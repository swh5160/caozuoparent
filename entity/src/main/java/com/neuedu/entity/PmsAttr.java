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
 * 商品属性表
 * </p>
 *
 * @author 施文慧
 * @since 2022-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PmsAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 0输入 1单选 2 多选
     */
    private Integer inputType;

    /**
     * 选择列表
     */
    private String inputList;

    /**
     * 1-spu 2-sku
     */
    private Integer type;

    public PmsAttr(Integer id, String name, Integer inputType, String inputList) {
        this.id = id;
        this.name = name;
        this.inputType = inputType;
        this.inputList = inputList;
    }

    public PmsAttr(String name, Integer categoryId, Integer inputType, String inputList, Integer type) {
        this.name = name;
        this.categoryId = categoryId;
        this.inputType = inputType;
        this.inputList = inputList;
        this.type = type;
    }
}
