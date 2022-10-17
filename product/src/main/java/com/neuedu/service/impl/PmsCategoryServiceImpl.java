package com.neuedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.config.MyException;
import com.neuedu.entity.PmsCategory;
import com.neuedu.mapper.PmsCategoryMapper;
import com.neuedu.service.PmsCategoryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author 施文慧
 * @since 2022-09-27
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements PmsCategoryService {

//    @Resource(name = "redisTemplate")
//    RedisTemplate<String, Object> redisTemplate;
    private List<PmsCategory> getByParentId(Integer parentId, Boolean active){
        QueryWrapper<PmsCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",parentId);
        if (active != null){
            wrapper.eq("active",active);
        }
        List<PmsCategory> list = this.list(wrapper);
        for (PmsCategory pmsCategory : list) {
            //递归一层一层往下
            pmsCategory.setChildren(this.getByParentId(pmsCategory.getId(),active));
        }
        return  list;
    }
    @Override
    @Cacheable(value = "pms",key = "'category'")
    public List<PmsCategory> get() {
        /*final String key = "pms::category";
        //判断缓存中是否有值
        if (redisTemplate.hasKey(key)) {
            String str = redisTemplate.opsForValue().get(key).toString();
            //反序列化,不用再查数据库
            List<PmsCategory> pmsCategories = JSONObject.parseArray(str, PmsCategory.class);
            return  pmsCategories;
        }
        List<PmsCategory> list = this.getByParentId(0);
        //集合转换成json
        redisTemplate.opsForValue().set(key,list,30, TimeUnit.DAYS);
        */
        return this.getByParentId(0,null);
    }

    @Override
    public List<PmsCategory> getActive() {
        return this.getByParentId(0,true);
    }



    @Override
    @CacheEvict(value = "pms",key = "'category'")
    public Boolean add(String name, Integer parentId, Integer level) {
        PmsCategory pmsCategory = new PmsCategory(
              name,parentId,level
        );
        return this.save(pmsCategory);
    }

    @Override
    @CacheEvict(value = "pms",key = "'category'")
    public Boolean update(Integer id, String name) {
        PmsCategory pmsCategory = new PmsCategory(
                id,name
        );
        return this.updateById(pmsCategory);
    }

    @Override
    @CacheEvict(value = "pms",key = "'category'")
    public Boolean del(Integer id, Boolean active) throws MyException {
        //查询子集是否删除
        QueryWrapper<PmsCategory> wrapper = new QueryWrapper<>();
        //如果active为真执行删除,否则恢复
        if (!active){
            wrapper.eq("parent_id",id)
                    .eq("active",1);
            if (this.count(wrapper) > 0){
                throw new MyException("存在未删除的下一级,无法删除当前类");
            }
        }else {
            //查上级谁的id=当前ParentId
            PmsCategory category = this.getById(id);
            //如果parentId不是父集
            if (category.getParentId() != 0){
                //查询当前子集的父类
                PmsCategory parent = this.getById(category.getParentId());
                //如果上一集无效数据
                if (!parent.getActive()){
                    throw new MyException("必须恢复上一级分类");
                }
            }
        }

        PmsCategory pmsCategory = new PmsCategory(id,active);
        return this.updateById(pmsCategory);
    }

    @Override
    public String getNameByIds(Integer[] categoryIds) {
        List<String> list = new ArrayList<>();
        for (Integer id : categoryIds) {
            list.add(this.getById(id).getName());
        }
        return String.join("/",list);
    }


}
