package com.hanna.ws.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    @Test
    public void testOrder() {
        Order order = new Order("Test Order", 100.0, true);
        
        assertEquals("Test Order", order.getOrderName());
        assertEquals(100.0, order.getPrice());
        assertTrue(order.getIsDiscounted());
        assertEquals(5.0, order.getDiscountPercentage());
    }

    @Test
    public void testSetters() {
        Order order = new Order();
        order.setOrderName("New Order");
        order.setPrice(150.0);
        order.setIsDiscounted(false);

        assertEquals("New Order", order.getOrderName());
        assertEquals(150.0, order.getPrice());
        assertFalse(order.getIsDiscounted());
    }
}