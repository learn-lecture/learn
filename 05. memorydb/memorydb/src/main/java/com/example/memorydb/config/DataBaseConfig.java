package com.example.memorydb.config;

import com.example.memorydb.user.db.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*  외부에 있는 Class를 사용할 때 new 생성자로 Bean을 생성.
@Configuration
public class DataBaseConfig {
    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }
}
*/

/*
Spring App이 실행될 때, Configuration을 찾아서 특정한 내용을
Spring Context라는 영역에 new 생성자를 통해서 객체를 만듦.
Service, Controller, Bean에 필요한 영역에 Spring이 알아서 주입함.
*/