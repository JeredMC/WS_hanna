package com.hanna.ws.controller;

import com.hanna.ws.entity.Order;
import com.hanna.ws.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class OrderAndBillingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderServiceImpl orderService;

    @InjectMocks
    private OrderAndBillingController controller;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetOrderList() throws Exception {
        Order order1 = new Order("Order 1", 10.0, false);
        Order order2 = new Order("Order 2", 15.0, true);

        when(orderService.getAllOrders()).thenReturn(Arrays.asList(order1, order2));

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderName").value("Order 1"))
                .andExpect(jsonPath("$[1].orderName").value("Order 2"));
    }

    @Test
    public void testAddOrder() throws Exception {
        mockMvc.perform(post("/api/orders")
                .contentType("application/json")
                .content("{\"orderName\": \"New Order\", \"price\": 20.0, \"isDiscounted\": false}"))
                .andExpect(status().isOk());

        verify(orderService, times(1)).saveOrder(any(Order.class));
    }

    @Test
    public void testUpdateOrder() throws Exception {
        Order order = new Order("Updated Order", 25.0, true);
        order.setId(1L);

        doReturn(order).when(orderService).saveOrder(any(Order.class));

        mockMvc.perform(put("/api/orders/1")
                .contentType("application/json")
                .content("{\"orderName\": \"Updated Order\", \"price\": 25.0, \"isDiscounted\": true}"))
                .andExpect(status().isOk());

        verify(orderService, times(1)).saveOrder(any(Order.class));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        mockMvc.perform(delete("/api/orders/1"))
                .andExpect(status().isOk());

        verify(orderService, times(1)).deleteOrder(1L);
    }

    @Test
    public void testGetTotalRegularBill() throws Exception {
        when(orderService.calculateTotalRegularBill()).thenReturn(25.0);

        mockMvc.perform(get("/api/orders/totalRegularBill"))
                .andExpect(status().isOk())
                .andExpect(content().string("25.0"));
    }

    @Test
    public void testGetTotalDiscountedBill() throws Exception {
        when(orderService.calculateTotalDiscountedBill()).thenReturn(24.5);

        mockMvc.perform(get("/api/orders/totalDiscountedBill"))
                .andExpect(status().isOk())
                .andExpect(content().string("24.5"));
    }
}