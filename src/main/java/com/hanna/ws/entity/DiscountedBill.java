package com.hanna.ws.entity;

public class DiscountedBill extends OrderBill {
    public DiscountedBill(CafeClerk clerk) {
        super(clerk);
    }

    @Override
    public double getTotalBill() {
        return getOrderList().stream().mapToDouble(order -> {
            if (order.getIsDiscounted()) {
                return order.getPrice() * (1 - order.getDiscountPercentage() / 100);
            } else {
                return order.getPrice();
            }
        }).sum();
    }
}