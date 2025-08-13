package com.cdac.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;

import lombok.Getter;
@Service

public class RazorpayService {

    private final RazorpayClient razorpayClient;

    @Getter
    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    public RazorpayService(@Value("${razorpay.key_id}") String keyId,
            @Value("${razorpay.key_secret}") String keySecret) throws Exception {
this.keyId = keyId;
this.keySecret = keySecret;
this.razorpayClient = new RazorpayClient(keyId, keySecret);
}

    // Create order
    public Order createOrder(int amountInPaise, String currency, String receipt) throws Exception {
        JSONObject options = new JSONObject();
        options.put("amount", amountInPaise); // amount in smallest currency unit (paise)
        options.put("currency", currency);
        options.put("receipt", receipt);
        options.put("payment_capture", 1); // auto capture payment
        return razorpayClient.orders.create(options);
    }

    // Verify payment signature to avoid fraud
    public boolean verifyPaymentSignature(String orderId, String paymentId, String signature) {
        try {
            JSONObject attributes = new JSONObject();
            attributes.put("razorpay_order_id", orderId);
            attributes.put("razorpay_payment_id", paymentId);
            attributes.put("razorpay_signature", signature);
            Utils.verifyPaymentSignature(attributes, keySecret);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
