package com.example.springsecurity.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springsecurity.pojo.User;
import com.example.springsecurity.pojo.UserDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 崔令雨
 * @since 2022/8/11
 */
@Service
public class UserDetailServiceImpl implements UserDetailService {

    private final UserService userService;

    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(StringUtils.hasText(username),
                        User::getUserName,
                        username));
        List<String> stringList = new ArrayList<>();
        stringList.add("test");
        return new UserDetail(user, stringList);
    }
}
