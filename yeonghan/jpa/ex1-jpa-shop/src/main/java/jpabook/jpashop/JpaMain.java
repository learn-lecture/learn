package jpabook.jpashop;

import jpabook.jpashop.domain.item.Book;

public class JpaMain {

    public static void main(String[] args) {
        TransactionManager.executeInTransaction(em -> {
            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");
            em.persist(book);
        });
    }

}