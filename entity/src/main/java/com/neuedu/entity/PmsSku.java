package com.neuedu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * sku表
 * </p>
 *
 * @author 施子安
 * @since 2022-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PmsSku implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * sku属性
     */
    @JSONField(name = "skus")
    private String item;

    /**
     * 商品售价
     */
    private BigDecimal price;

    /**
     * 库存数
     */
    private Integer stock;

    /**
     * 商品id
     */
    private Integer productId;


}
