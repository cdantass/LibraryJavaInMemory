package caua.service;

import caua.model.Book;
import caua.model.Client;
import caua.model.Order;
import caua.model.StatusOrder;
import caua.repository.OrderRepository;

import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Client client, Book book) {

        boolean bookAlreadyBorrowed = orderRepository.findByBook(book).stream().anyMatch(order -> order.getStatus() == StatusOrder.CONFIRMED);

        if (bookAlreadyBorrowed){
            throw new IllegalStateException("Book already borrowed");
        }
        Order order = new Order(null, client, book);
        return orderRepository.save(order);
    }

    public List<Order> findAllOrders(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        return orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order ID not found"));
    }

    public Order confirmBorrow(Long orderId){
        Order order = findById(orderId);
        order.borrow();
        return order;
    }

    public Order cancelBorrow(Long orderId){
        Order order = findById(orderId);
        order.cancelBorrow();
        return order;
    }
}