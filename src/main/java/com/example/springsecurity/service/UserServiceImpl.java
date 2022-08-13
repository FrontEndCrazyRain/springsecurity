package com.example.springsecurity.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springsecurity.mapper.UserMapper;
import com.example.springsecurity.pojo.User;
import com.example.springsecurity.pojo.UserDetail;
import com.example.springsecurity.util.JwtUtil;
import com.example.springsecurity.util.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 崔令雨
 * @since 2022/8/11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
