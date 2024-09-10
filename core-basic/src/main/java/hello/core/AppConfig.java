package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Primary
    @Bean
    public MemberService memberService() {
        System.out.println("memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Primary
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("memberRepository");
        return new MemoryMemberRepository();
    }

    @Primary
    @Bean
    public OrderService orderService() {
        System.out.println("orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Primary
    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("discountPolicy");
        return new FixDiscountPolicy();
    }

}
