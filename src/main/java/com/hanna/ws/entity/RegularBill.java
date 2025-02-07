package com.hanna.ws.entity;

public class RegularBill extends OrderBill {
    public RegularBill(CafeClerk clerk) {
        super(clerk);
    }

    @Override
    public double getTotalBill() {
        return getOrderList().stream().mapToDouble(Order::getPrice).sum();
    }
}