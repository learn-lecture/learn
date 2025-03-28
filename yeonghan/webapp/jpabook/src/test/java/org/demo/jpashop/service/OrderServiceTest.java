package org.demo.jpashop.service;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.fail;

import jakarta.persistence.EntityManager;
import org.demo.jpashop.domain.Address;
import org.demo.jpashop.domain.Member;
import org.demo.jpashop.domain.Order;
import org.demo.jpashop.domain.OrderStatus;
import org.demo.jpashop.domain.item.Book;
import org.demo.jpashop.exception.NotEnoughStockException;
import org.demo.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired private EntityManager em;
    @Autowired private OrderService orderService;
    @Autowired private OrderRepository orderRepository;

    @Test
    void 상품_주문() {
        // given
        Member member = createMember();

        Book book = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;

        // when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // then
        Order order = orderRepository.findOne(orderId);
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, order.getStatus());
        assertEquals("주문한 상 종류 수가 정확해야 한다.", 1, order.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10000 * orderCount, order.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야한다.", 10 - orderCount, book.getStockQuantity());
    }

    @Test
    void 상품주문_재고수량_초과() {
        // given
        Member member = createMember();
        Book book = createBook("시골 JPA", 10000, 10);
        int orderCount = 11;

        // when & then
        Assertions.assertThrows(NotEnoughStockException.class, () ->
                orderService.order(member.getId(), book.getId(), orderCount));
    }

    @Test
    void 상품주문_취소() {
        // given
        Member member = createMember();
        Book book = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // when
        orderService.cancelOrder(orderId);

        // then
        Order order = orderRepository.findOne(orderId);
        assertEquals("주문 취소시 상태는 CANCEL이다.", OrderStatus.CANCEL, order.getStatus());
        assertEquals("주문 취소된 상품은 재고가 복구되어야 한다.", 10, book.getStockQuantity());
    }

    private Book createBook(String name, int price, int quantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(quantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }

}