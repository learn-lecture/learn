package com.learn.hellojpql;

import java.util.List;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class Jpql2 {

    public static void main(String[] args) {
        TransactionManager.executeInTransaction(em -> {
            Member member = new Member();
            member.setName("jihwan");
            member.setAge(1);
            em.persist(member);
            em.flush();
            em.clear();

            // 영속성 컨텍스트에서 관리 됨
            List<Member> res = em.createQuery("select m from Member m", Member.class).getResultList();
            res.getFirst().setAge(20);

            // dto로 여러값 프로젝션
            MemberDto result = em.createQuery("""
                    select new com.learn.hellojpql.MemberDto(m.name, m.age) 
                    from Member m
                    """, MemberDto.class
                )
                .getSingleResult();
            System.out.println("result = " + result);
        });
    }
}