package com.learn.hellojpql;

import java.util.List;

public class Jpql4 {
	public static void main(String[] args) {
		TransactionManager.executeInTransaction(em -> {
			Member member = new Member();
			member.setName("admin1");
			em.persist(member);

			Member member2 = new Member();
			member2.setName("admin2");
			em.persist(member2);

			em.flush();
			em.clear();

			String query = "select my_function(m.name) from Member m";
			List<String> res = em.createQuery(query, String.class).getResultList();

			for (String re : res) {
				System.out.println(re);
			}
		});
	}
}
