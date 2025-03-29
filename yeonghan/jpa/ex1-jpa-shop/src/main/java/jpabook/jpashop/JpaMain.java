package jpabook.jpashop;

public class JpaMain {

    public static void main(String[] args) {
        TransactionManager.executeInTransaction(em -> {
        });
    }

}