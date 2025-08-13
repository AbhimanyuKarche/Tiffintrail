package com.cdac.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.entity.CustomerProfile;
import com.cdac.entity.Order;
import com.cdac.enums.OrderStatus;
import com.cdac.services.CustomerProfileService;
import com.cdac.services.OrderService;
import com.cdac.services.RazorpayService;
import com.cdac.services.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final RazorpayService razorpayService;
    private final CustomerProfileService customerProfileService; // Use your interface

    @Autowired
    public OrderController(OrderService orderService, RazorpayService razorpayService, CustomerProfileService customerService, CustomerProfileService customerProfileService) {
        this.orderService = orderService;
        this.razorpayService = razorpayService;
        this.customerProfileService = customerProfileService;

    }

    // Place order and create Razorpay order
    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(Authentication auth) {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        CustomerProfile customer = userDetails.getUser().getCustomerProfile();

        if (customer == null) {
            return ResponseEntity.status(400).body(Map.of("error", "Customer profile not found"));
        }

        try {
            Order order = orderService.placeOrder(customer);

            return ResponseEntity.ok(Map.of(
                    "orderId", order.getId(),
                    "razorpayOrderId", order.getRazorpayOrderId(),
                    "amount", (int) (order.getTotalAmount() * 100), // amount in paise
                    "currency", "INR",
                    "key", razorpayService.getKeyId(),
                    "status", order.getStatus()
            ));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Internal Server Error"));
        }
    }

    // Get orders for logged-in customer
    @GetMapping("/my-orders")
    public ResponseEntity<List<Order>> getMyOrders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        CustomerProfile customer = userDetails.getUser().getCustomerProfile();
        List<Order> orders = orderService.getOrdersByCustomer(customer);
        return ResponseEntity.ok(orders);
    }

    // Admin: Get order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return ResponseEntity.ok(order);
    }

    // Verify payment signature and update order status
    @PostMapping("/payment/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> payload) {
        String razorpayOrderId = payload.get("razorpay_order_id");
        String razorpayPaymentId = payload.get("razorpay_payment_id");
        String razorpaySignature = payload.get("razorpay_signature");

        boolean isValid = razorpayService.verifyPaymentSignature(razorpayOrderId, razorpayPaymentId, razorpaySignature);

        if (!isValid) {
            return ResponseEntity.status(400).body(Map.of("status", "failure", "message", "Invalid payment signature"));
        }

        Optional<Order> orderOpt = orderService.getOrderByRazorpayOrderId(razorpayOrderId);
        if (orderOpt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("status", "failure", "message", "Order not found"));
        }

        Order order = orderOpt.get();
        order.setStatus(OrderStatus.PLACED); // or PAID depending on your enum
        orderService.save(order);

        return ResponseEntity.ok(Map.of("status", "success"));
    }
}
