package com.cdac.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Repositories.CustomerRepository;
import com.cdac.dto.OrderRequestDto;
import com.cdac.dto.OrderResponseDto;
import com.cdac.entity.CustomerProfile;
import com.cdac.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CustomerRepository customerRepo;
    private final ModelMapper modelMapper;

 // POST: Place order
    @PostMapping("/{customerId}")
    public ResponseEntity<OrderResponseDto> placeOrder(@PathVariable Long customerId,
                                                       @RequestBody OrderRequestDto dto) {
        CustomerProfile customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Set customer inside DTO
        dto.setCustomer(customer);

        // Directly call service — conversion handled in service
        OrderResponseDto responseDto = orderService.placeOrder(dto);

        return ResponseEntity.ok(responseDto);
    }

    // GET: All orders of a customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByCustomer(@PathVariable Long customerId) {
        CustomerProfile customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        List<OrderResponseDto> list = orderService.getOrdersByCustomer(customer);
        return ResponseEntity.ok(list);
    }

    // GET: Order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long orderId) {
        Optional<OrderResponseDto> optional = orderService.getOrderById(orderId);
        return optional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
