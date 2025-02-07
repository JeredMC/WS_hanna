package com.hanna.ws.controller;

import com.hanna.ws.entity.Order;
import com.hanna.ws.impl.OrderServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderAndBillingController {

    private final OrderServiceImpl orderService;

    public OrderAndBillingController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrderList() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public void addOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody Order order) {
        order.setId(id);
        orderService.saveOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/totalRegularBill")
    public double getTotalRegularBill() {
        return orderService.calculateTotalRegularBill();
    }

    @GetMapping("/totalDiscountedBill")
    public double getTotalDiscountedBill() {
        return orderService.calculateTotalDiscountedBill();
    }
}