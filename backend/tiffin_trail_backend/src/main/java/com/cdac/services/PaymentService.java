package com.cdac.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.cdac.entity.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentService {

    private RazorpayClient razorpayClient;

    public PaymentService() throws Exception {
        this.razorpayClient = new RazorpayClient("YOUR_KEY_ID", "YOUR_KEY_SECRET");
    }

    public String createOrder(int amount) throws Exception {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100); // amount in paise
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "txn_" + System.currentTimeMillis());

        com.razorpay.Order order = razorpayClient.orders.create(orderRequest);
        return order.toString();
    }
}
