package com.unir.d1001.orders.controllers;

import com.unir.d1001.orders.dto.CreateOrderRequest;
import com.unir.d1001.orders.dto.ItemDto;
import com.unir.d1001.orders.dto.ProductDto;
import com.unir.d1001.orders.entities.Order;
import com.unir.d1001.orders.entities.OrderItem;
import com.unir.d1001.orders.repositories.OrderRepository;
import com.unir.d1001.orders.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(order -> ResponseEntity.ok().body(order))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {
        List<OrderItem> orderItems = new ArrayList<>();

        Order order = Order.builder()
                .userId(1L)
                .total(0.0f)
                .subtotal(0.0f)
                .iva(0.0f)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        for (ItemDto item : request.items) {
            ProductDto product = productService.getProductById(item.productId()).orElse(null);
            if (product == null) {
                return ResponseEntity.badRequest().build();
            }
            OrderItem orderItem = OrderItem.builder()
                    .productId(product.id)
                    .quantity(item.quantity())
                    .price(product.precio)
                    .subtotal(product.precio * item.quantity())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .order(order)
                    .build();

            orderItems.add(orderItem);
        }

        order.setSubtotal((float)orderItems.stream().mapToDouble(OrderItem::getSubtotal).sum());
        order.setIva((float)(order.getSubtotal() * 0.16));
        order.setTotal(order.getSubtotal() + order.getIva());
        order.setOrderItems(orderItems);

        orderRepository.save(order);

        return ResponseEntity.ok(order);
    }
}
