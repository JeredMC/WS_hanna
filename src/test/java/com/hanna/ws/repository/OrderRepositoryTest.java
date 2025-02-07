package com.hanna.ws.repository;

import com.hanna.ws.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testAddOrder() {
        Order order = new Order("Test Order", 100.0, true);
        orderRepository.save(order);

        Optional<Order> foundOrder = orderRepository.findById(order.getId());
        assertTrue(foundOrder.isPresent());
        assertEquals("Test Order", foundOrder.get().getOrderName());
    }

    @Test
    public void testUpdateOrder() {
        Order order = new Order("Test Order", 100.0, true);
        orderRepository.save(order);

        order.setOrderName("Updated Order");
        orderRepository.save(order);

        Optional<Order> foundOrder = orderRepository.findById(order.getId());
        assertTrue(foundOrder.isPresent());
        assertEquals("Updated Order", foundOrder.get().getOrderName());
    }

    @Test
    public void testDeleteOrder() {
        Order order = new Order("Test Order", 100.0, true);
        orderRepository.save(order);
        
        orderRepository.deleteById(order.getId());

        Optional<Order> foundOrder = orderRepository.findById(order.getId());
        assertFalse(foundOrder.isPresent());
    }
}
