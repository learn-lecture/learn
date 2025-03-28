package org.demo.jpashop;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.demo.jpashop.domain.Address;
import org.demo.jpashop.domain.Delivery;
import org.demo.jpashop.domain.Member;
import org.demo.jpashop.domain.Order;
import org.demo.jpashop.domain.OrderItem;
import org.demo.jpashop.domain.item.Book;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Member member = getMember("userA", "서울", "1", "1111");
            em.persist(member);

            Book book1 = new Book();
            extractBook(book1, "JPA1 BOOK", 10000, 100);
            em.persist(book1);

            Book book2 = new Book();
            extractBook(book2, "JPA2 BOOK", 20000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Order order = getOrder(member, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2() {
            Member member = getMember("userB", "진주", "1", "1111");
            em.persist(member);

            Book book1 = new Book();
            extractBook(book1, "SPRING1 BOOK", 20000, 200);
            em.persist(book1);

            Book book2 = new Book();
            extractBook(book2, "SPRING2 BOOK", 40000, 300);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Order order = getOrder(member, orderItem1, orderItem2);
            em.persist(order);
        }

        private static Order getOrder(Member member, OrderItem orderItem1, OrderItem orderItem2) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());

            return Order.createOrder(member, delivery, orderItem1, orderItem2);
        }

        private static void extractBook(Book book1, String JPA1_BOOK, int price, int quantity) {
            book1.setName(JPA1_BOOK);
            book1.setPrice(price);
            book1.setStockQuantity(quantity);
        }

        private static Member getMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

    }

}

