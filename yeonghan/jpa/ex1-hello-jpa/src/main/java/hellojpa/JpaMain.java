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

            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
