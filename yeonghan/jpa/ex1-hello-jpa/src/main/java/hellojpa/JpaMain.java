package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*
            // INSERT
            Member member = new Member();
            member.setId(1L);
            member.setName("helloA");

            em.persist(member);
            */
            /*
            // SELECT
            Member findMember = em.find(Member.class, 1L);
            System.out.println("member id = " + findMember.getId());
            System.out.println("member name = " + findMember.getName());
             */
            /*
            // DELETE
            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);
             */
            /*
            // UPDATE
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("helloJPA");
            */

            /*List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }*/
            // 비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("Test");

            // 영속
            System.out.println("=== BEFORE ===");
            em.persist(member);
            System.out.println("=== AFTER ===");

            Member findedMember = em.find(Member.class, 101L); // Select Query 안나감

            Member member1 = em.find(Member.class, 102L);// Select Query 나감
            Member member2 = em.find(Member.class, 102L);// 안나감

            em.remove(em.find(Member.class, 101L));

            // 준영속
            Member newMember = new Member();
            newMember.setId(101L);
            newMember.setName("Test");
            em.persist(member);
            em.flush(); // DB에 Member 정보가 저장됨

            em.detach(member);
            member.setName("Test2"); // Commit시 DB에 수정된 정보가 반영되지 않음.

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
