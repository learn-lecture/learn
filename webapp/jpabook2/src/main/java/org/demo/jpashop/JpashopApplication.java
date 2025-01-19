package org.demo.jpashop;


import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module.Feature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpashopApplication.class, args);
    }

    @Bean
    Hibernate6Module hibernate5Module() {
        Hibernate6Module hibernate6Module = new Hibernate6Module();
        //hibernate6Module.configure(Feature.FORCE_LAZY_LOADING, true);
        // force는 좋지않음. 필요하지 않은 LAZY까지 모두 다 강제로 불러오게 됨.
        return hibernate6Module;
    }

}
