package org.demo.jpashop;

import org.assertj.core.api.Assertions;
import org.demo.jpashop.domain.Member;
import org.demo.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void testMember() {
        // given
        Member member = new Member();

        // when
        Member findMember = memberRepository.findOne(1L);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
    }

}