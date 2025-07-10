package com.learn.hellojpql;

import java.util.List;

public class Jpql3 {

    public static void main(String[] args) {
        TransactionManager.executeInTransaction(em -> {
            for (int i = 0; i < 100; i++) {
                Member member = new Member();
                member.setName("jihwan" + i);
                member.setAge(i);
                em.persist(member);
            }
            em.flush();
            em.clear();

            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
                .setFirstResult(0)
                .setMaxResults(10)
                .getResultList();
            System.out.println("result = " + result.size());
            for (Member member1 : result) {
                System.out.println("member1 = " + member1);
            }
        });
    }
}