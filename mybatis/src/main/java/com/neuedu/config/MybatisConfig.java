package com.neuedu.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 施子安
 * @create
 */
@Configuration
public class MybatisConfig {

    /*
     * 分页配置
     * */
    @Bean
    MybatisPlusInterceptor getInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //拦截分页选择数据库类型
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
