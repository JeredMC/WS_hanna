package com.hanna.ws.impl;

import com.hanna.ws.entity.Order;
import com.hanna.ws.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public double calculateTotalRegularBill() {
        return orderRepository.findAll().stream()
                .mapToDouble(Order::getPrice)
                .sum();
    }

    public double calculateTotalDiscountedBill() {
        return orderRepository.findAll().stream()
                .mapToDouble(order -> {
                    if (order.getIsDiscounted()) {
                        return order.getPrice() * (1 - order.getDiscountPercentage() / 100);
                    } else {
                        return order.getPrice();
                    }
                }).sum();
    }
}
