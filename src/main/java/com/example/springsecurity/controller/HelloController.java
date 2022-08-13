package com.example.springsecurity.controller;

import com.alibaba.fastjson.JSON;
import com.example.springsecurity.pojo.User;
import com.example.springsecurity.pojo.UserDetail;
import com.example.springsecurity.util.JwtUtil;
import com.example.springsecurity.util.RedisCache;
import com.example.springsecurity.util.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 崔令雨
 * @since 2022/8/11
 */
@RestController
public class HelloController {

    public HelloController(AuthenticationManager authenticationManager, RedisCache redisCache) {
        this.authenticationManager = authenticationManager;
        this.redisCache = redisCache;
    }

    @GetMapping("hello")
    @PreAuthorize("hasAuthority('curit')")
    public String login() {
        return "hello";
    }

    private final AuthenticationManager authenticationManager;

    private final RedisCache redisCache;

    @PostMapping("user/login")
    public ResponseResult<Map<String, Object>> login(@RequestBody User user) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码为空");
        }
        UserDetail principal = (UserDetail) authenticate.getPrincipal();

        Long id = principal.getUser().getId();

        String jwt = JwtUtil.createJWT(id.toString());

        redisCache.setCacheObject(id.toString(), principal);

        Map<String, Object> map = new HashMap<>();
        map.put("token", jwt);
        return new ResponseResult<>(200, "返回token", map);
    }

    @PostMapping("user/logout")
    public ResponseResult<?> signOut() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        System.out.println("principal.toString() = " + principal.toString());
        UserDetail userDetail = JSON.parseObject(principal.toString(), UserDetail.class);
        redisCache.deleteObject(userDetail.getUser().getId().toString());
        return new ResponseResult<>(200, "退出成功");
    }


}
