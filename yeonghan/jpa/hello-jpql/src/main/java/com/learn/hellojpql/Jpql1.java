package com.learn.hellojpql;

import java.util.List;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class Jpql1 {

    public static void main(String[] args) {
        TransactionManager.executeInTransaction(em -> {
            Member member = new Member();
            member.setName("jihwan");
            member.setAge(1);
            em.persist(member);

            // type<t>, query
            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
            TypedQuery<String> query2 = em.createQuery("select m.name from Member m", String.class);
            Query query3 = em.createQuery("select m.name, m.age from Member m");

            // list, single
            List<Member> resultList = query1.getResultList();
            Member singleResult = query1.getSingleResult();

            // binding (can chaining)
            Member result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", "jihwan")
                .getSingleResult();
            System.out.println("result = " + result.getName());
        });
    }

}