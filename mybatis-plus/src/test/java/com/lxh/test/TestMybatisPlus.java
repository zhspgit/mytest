package com.lxh.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxh.entity.User;
import com.lxh.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMybatisPlus {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void select(){
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void selectById(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }
    @Test
    public void selectByIds(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<User> users = userMapper.selectBatchIds(list);
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void selectByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("user_name","zs");
        map.put("userage",23);
        List<User> users = userMapper.selectByMap(map);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 查询姓名like an并且年龄小于等于25
     */
    @Test
    public void selectByWrapper(){
        QueryWrapper<User> query = Wrappers.<User>query();
        query.like("user_name","an").le("userage",25);
        List<User> users = userMapper.selectList(query);
        for (User user : users) {
            System.out.println(user);
        }
    }
    /**
     * 查询姓名like an并且年龄大于等于23并且小于等于26(只查id和user_name列)
     */
    @Test
    public void selectByWrapper2(){
        QueryWrapper<User> query = Wrappers.<User>query();
        query.select("userid","user_name").like("user_name","an").between("userage",23,26);
        List<User> users = userMapper.selectList(query);
        for (User user : users) {
            System.out.println(user);
        }
    }
    /**
     * 查询姓名不为空并且年龄大于等于24，年龄相同的按照userid升序
     */
    @Test
    public void selectByWrapper3(){
        QueryWrapper<User> query = Wrappers.<User>query();
        query.isNotNull("user_name").ge("userage",24).orderByAsc("userid");
        List<User> users = userMapper.selectList(query);
        for (User user : users) {
            System.out.println(user);
        }
    }
    /**
     * 查询姓名不为空并且年龄大于等于24，年龄相同的按照userid升序
     */
    @Test
    public void condition(){
        String username = "ng";
        int userage = 23;
       QueryWrapper<User> query = Wrappers.<User>query();
      /* if (!StringUtils.isEmpty(username)){
           query.like("user_name",username);
       }
        if (!StringUtils.isEmpty(userage)){
            query.ge("userage",userage);
        }*/
        query.like(!StringUtils.isEmpty(username),"user_name",username).ge(!StringUtils.isEmpty(userage),"userage",userage);
        List<User> users = userMapper.selectList(query);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 查询姓名like an并且年龄小于等于25（返回map，null值不返回）
     */
    @Test
    public void selectByWrapper4(){
        QueryWrapper<User> query = Wrappers.<User>query();
        query.select("userid","userage").like("user_name","an").le("userage",25);
       /* List<User> users = userMapper.selectList(query);
        for (User user : users) {
            System.out.println(user);
        }*/
        List<Map<String, Object>> maps = userMapper.selectMaps(query);
        System.out.println(maps);
    }
    /**
     * 查询姓名like an并且年龄小于等于25（返回map，null值不返回）
     */
    @Test
    public void selectByLambda(){
        LambdaQueryWrapper<User> query = Wrappers.<User>lambdaQuery();
        //query.select("userid","userage").like("user_name","an").le("userage",25);
       /* List<User> users = userMapper.selectList(query);
        for (User user : users) {
            System.out.println(user);
        }*/
       query.like(User:: getUsername, "ng").le(User:: getUserage, 25);
        List<Map<String, Object>> maps = userMapper.selectMaps(query);
        System.out.println(maps);
    }
    /**
     * 查询姓名like an并且年龄小于等于25的数量
     */
    @Test
    public void selectByWrapperCount(){
        QueryWrapper<User> query = Wrappers.<User>query();
        query.like("user_name","an").le("userage",25);
        Integer count = userMapper.selectCount(query);
        System.out.println(count);
    }
    /**
     * 分页查询年龄小于等于25
     */
    @Test
    public void selectPage(){
        QueryWrapper<User> query = new QueryWrapper<>();
        query.le("userage",25);
        Page<User> page = new Page<>(1,2);//当前类，每页显示数量
        IPage<User> userPage = userMapper.selectPage(page, query);
        System.out.println("总页数："+userPage.getPages());
        System.out.println("总数量："+userPage.getTotal());
        System.out.println("总记录数："+userPage.getRecords());
    }
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("wangwu");
        user.setUserage(25);
        int count = userMapper.insert(user);
        System.out.println(count);
    }
    @Test
    public void updateById(){
        User user = new User();
        user.setUsername("zhangsan3");
        user.setUserage(23);
        user.setUserid(20);
        int count = userMapper.updateById(user);
        System.out.println(count);
    }
    @Test
    public void updateByWrapper(){
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("userage",26).eq("user_name","wangwu");
        User user = new User();
        user.setUserage(23);
        int count = userMapper.update(user,query);
        System.out.println(count);
    }
    @Test
    public void deleteById(){
        int count = userMapper.deleteById(28);
        System.out.println(count);
    }
    @Test
    public void deleteBatchIds(){
        List<Integer> list = Arrays.asList(9, 11, 13);
        int count = userMapper.deleteBatchIds(list);
        System.out.println(count);
    }
}
