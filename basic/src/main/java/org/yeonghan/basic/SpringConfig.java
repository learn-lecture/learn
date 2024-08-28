package org.yeonghan.basic;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yeonghan.basic.repository.JdbcMemberRepository;
import org.yeonghan.basic.repository.MemberRepository;
import org.yeonghan.basic.repository.MemoryMemberRepository;
import org.yeonghan.basic.service.MemberService;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(MemberRepository memberRepository) {
        return new MemberService(memberRepository);
    }

    @Bean
    public MemberRepository memberRepository(DataSource dataSource) {
        return new JdbcMemberRepository(dataSource);
    }

}
