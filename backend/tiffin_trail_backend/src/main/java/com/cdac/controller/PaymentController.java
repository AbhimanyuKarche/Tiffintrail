package com.cdac.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

//    private final PaymentService paymentService;
//
//    public PaymentController(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
//
//    @PostMapping("/create-order")
//    public ResponseEntity<?> createOrder(@RequestBody Map<String, Integer> data) {
//        try {
//            int amount = data.get("amount");
//            String order = paymentService.createOrder(amount);
//            return ResponseEntity.ok(order);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
//        }
//    }
}
