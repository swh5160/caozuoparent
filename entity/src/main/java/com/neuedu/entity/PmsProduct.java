package com.neuedu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author 施子安
 * @since 2022-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 品牌id
     */
    private Integer brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 分类id
     */
    private String categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 商品大图
     */
    private String pic;

    /**
     * 商品相册
     */
    private String images;

    /**
     * spu属性
     */
    private String spuAttr;

    /**
     * sku属性
     */
    private String skuAttr;

    /**
     * 商品详情
     */
    private String description;

    /**
     * 是否新品
     */
    private Boolean isNew;

    /**
     * 是否热卖
     */
    private Boolean isHot;

    /**
     * 是否上架
     */
    private Boolean isPublish;

    /**
     * 状态是否热卖
     */
    private Boolean active;


}
