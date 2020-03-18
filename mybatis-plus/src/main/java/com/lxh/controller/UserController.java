package com.lxh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    String username = "zhangsan";
    int age =233;
    String password = "123456";
    int num = 0;
}
