package com.hanna.ws.impl;

import com.hanna.ws.entity.Order;
import com.hanna.ws.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        Order order1 = new Order("Order 1", 10.0, false);
        Order order2 = new Order("Order 2", 15.0, true);

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderService.getAllOrders();
        assertEquals(2, orders.size());
        assertEquals("Order 1", orders.get(0).getOrderName());
        assertEquals("Order 2", orders.get(1).getOrderName());
    }

    @Test
    public void testSaveOrder() {
        Order order = new Order("Order", 20.0, false);
        when(orderRepository.save(order)).thenReturn(order);

        Order savedOrder = orderService.saveOrder(order);
        assertEquals("Order", savedOrder.getOrderName());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testDeleteOrder() {
        Long id = 1L;
        orderService.deleteOrder(id);
        verify(orderRepository, times(1)).deleteById(id);
    }

    @Test
    public void testCalculateTotalRegularBill() {
        Order order1 = new Order("Order 1", 10.0, false);
        Order order2 = new Order("Order 2", 15.0, false);

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        double totalBill = orderService.calculateTotalRegularBill();
        assertEquals(25.0, totalBill);
    }

    @Test
    public void testCalculateTotalDiscountedBill() {
        Order order1 = new Order("Order 1", 10.0, true);
        Order order2 = new Order("Order 2", 15.0, false);

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        double totalBill = orderService.calculateTotalDiscountedBill();
        assertEquals(24.5, totalBill);
    }
}