package com.neuedu.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author 施子安
 * @create
 * 跨域拦截
 */
@Configuration
public class BaseConfig {
    /*
     * 跨域拦截
     * */
   /* @Bean
    CorsFilter getCorsFilter(){
        //1、地址栏
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //3、添加规则
        CorsConfiguration configuration = new CorsConfiguration();
        //允许请求地址
        configuration.addAllowedOrigin("http://127.0.0.1");
        //允许请求方式
        configuration.addAllowedMethod("*");
        //允许请求头
        configuration.addAllowedHeader("*");
        //2、注册过滤地址
        source.registerCorsConfiguration("/**",configuration);

        return new CorsFilter(source);
    }
*/
}
