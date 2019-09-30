package com.tttiger.admin.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/09/29 15:32
 * @Description
 */
@Configuration
@MapperScan("com.tttiger.admin.mapper")
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        // 开启驼峰命名
        return configuration -> {
            configuration.setLogImpl(StdOutImpl.class);
            configuration.setMapUnderscoreToCamelCase(true);
        };
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}