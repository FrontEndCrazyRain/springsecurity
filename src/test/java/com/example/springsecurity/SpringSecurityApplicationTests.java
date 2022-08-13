package com.example.springsecurity;

import com.example.springsecurity.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SpringSecurityApplicationTests {


    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    SpringSecurityApplicationTests(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Test
    void contextLoads() {
        System.out.println("userMapper.selectList(null) = " + userMapper.selectList(null));

    }

}
