package com.lxh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("com.lxh.mapper")
public class MybatisPlus {
	//Ö÷·½·¨
    public static void main(String[] args){
        SpringApplication.run(MybatisPlus.class, args);
    }
}
