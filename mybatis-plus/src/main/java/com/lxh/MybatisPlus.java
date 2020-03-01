package com.lxh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("com.lxh.mapper")
public class MybatisPlus {
    /**
     *  main方法1
     * @param args
     */
    public static void main(String[] args){
        SpringApplication.run(MybatisPlus.class, args);
    }

    /**
     *  测试
     */
    public void test(){

    }
}
