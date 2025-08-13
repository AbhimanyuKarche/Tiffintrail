package com.cdac.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Repositories.CartItemRepository;
import com.cdac.Repositories.CartRepository;
import com.cdac.Repositories.OrderItemRepository;
import com.cdac.Repositories.OrderRepository;
import com.cdac.entity.Cart;
import com.cdac.entity.CartItem;
import com.cdac.entity.CustomerProfile;
import com.cdac.entity.Order;
import com.cdac.entity.OrderItem;
import com.cdac.enums.OrderStatus;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final CartItemRepository cartItemRepo;
    private final CartRepository cartRepo;


    // existing fields
    private final RazorpayService razorpayService;  // inject your Razorpay service

   

    @Override
    @Transactional
    public Order placeOrder(CustomerProfile customer) throws Exception {

        Cart cart = cartRepo.findByCustomerWithItems(customer)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<CartItem> cartItems = cart.getItems();

        if (cartItems == null || cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);  // new status

        double total = 0.0;

        order = orderRepo.save(order);

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setTiffin(cartItem.getTiffin());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getTiffin().getPrice());

            orderItemRepo.save(orderItem);

            total += cartItem.getTiffin().getPrice() * cartItem.getQuantity();
        }

        order.setTotalAmount(total);

        // Create Razorpay order (amount in paise)
        int amountInPaise = (int)(total * 100);

        com.razorpay.Order razorpayOrder = razorpayService.createOrder(amountInPaise, "INR", "order_rcptid_" + order.getId());

        // Save razorpay order id in your order entity
        order.setRazorpayOrderId(razorpayOrder.get("id"));
        order = orderRepo.save(order);

        // Clear cart
        cart.getItems().clear();
        cartItemRepo.deleteAllByCart(cart);

        return order;
    }
    @Override
    public Optional<Order> getOrderByRazorpayOrderId(String razorpayOrderId) {
        return orderRepo.findByRazorpayOrderId(razorpayOrderId);
    }

    @Override
    public List<Order> getOrdersByCustomer(CustomerProfile customer) {
        return List.of();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return Optional.empty();
    }
    @Override
    public Order save(Order order) {
        return orderRepo.save(order);
    }
}