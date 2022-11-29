package com.neuedu.controller;


import com.alibaba.fastjson.JSONObject;
import com.neuedu.common.ResultJson;
import com.neuedu.entity.PmsProduct;
import com.neuedu.entity.PmsSku;
import com.neuedu.service.*;
import io.minio.errors.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author 施子安
 * @since 2022-09-28
 */
@RestController
@RequestMapping("/pmsProduct")
public class PmsProductController {
    @Resource
    PmsBrandService pmsBrandService;
    @Resource
    PmsCategoryService pmsCategoryService;
    //属性service
    @Resource
    PmsAttrService pmsAttrService;
    @Resource
    FileService fileService;
    @Resource
    PmsProductService pmsProductService;
    @Resource
    PmsSkuService pmsSkuService;

    @GetMapping("/list")
    ResultJson list(Integer pageNo,Integer pageSize,String value){
        return ResultJson.success(pmsProductService.list(pageNo,pageSize,value));
    }
    /*
    * 初始化加载两个数据，全部+状态存在
    * */
    @GetMapping("/getInit")
    ResultJson getInit(){
        Map<String, List> map = new HashMap<>();
        map.put("brands",pmsBrandService.getAll());
        map.put("categories",pmsCategoryService.getActive());
        return ResultJson.success(map);
    }
    @GetMapping("/getAttr")
    ResultJson getAttr(Long[] categoryIds){
        return  ResultJson.success(pmsAttrService.getActiveByCategoryIds(categoryIds));
    }
    @PostMapping("/add")
    @Transactional
    ResultJson add(String name, Integer brandId, Integer[] categoryId, String description, MultipartFile file, String[] files, String[] skuList, BigDecimal price, String[] spus) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InternalException {
        List<PmsSku> skus = new ArrayList<>();

        PmsProduct pmsProduct = new PmsProduct();
        pmsProduct.setName(name);
        pmsProduct.setBrandId(brandId);
        pmsProduct.setBrandName(pmsBrandService.getNameById(brandId));
        pmsProduct.setCategoryId(Arrays.toString(categoryId));
        pmsProduct.setCategoryName(pmsCategoryService.getNameByIds(categoryId));
        pmsProduct.setPic(fileService.upload("logo",file));
        pmsProduct.setImages(Arrays.toString(files));
        pmsProduct.setSpuAttr(Arrays.toString(files));
        pmsProduct.setSkuAttr(Arrays.toString(skuList));
        pmsProduct.setDescription(description);
        pmsProduct.setPrice(price);
        pmsProductService.save(pmsProduct);
        for (String str : skuList) {
            //反序列化
            PmsSku pmsSkus = JSONObject.parseObject(str, PmsSku.class);
            pmsSkus.setProductId(pmsProduct.getId());
            skus.add(pmsSkus);
        }
        return ResultJson.success(pmsSkuService.saveBatch(skus),"添加商品成功");
    }
}
