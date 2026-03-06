package caua.repository;

import caua.model.Book;
import caua.model.Order;
import caua.model.StatusOrder;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class OrderInMemory implements OrderRepository{

    private final Map<Long, Order> orderMap = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);


    @Override
    public Order save(Order order){
        if(order.getId() == null){
            order.setId(idGenerator.getAndIncrement());
        }
        orderMap.put(order.getId(), order);

        return order;
    }

    @Override
    public List<Order> findAll(){
        return new ArrayList<>(orderMap.values());
    }

    @Override
    public Optional<Order> findById(Long id){
        return orderMap.values().stream().filter(order -> order.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Order> findByStatus(StatusOrder status){
        return orderMap.values().stream().filter(order -> order.getStatus() == status).findFirst();
    }

    @Override
    public List<Order> findByBook(Book book){
        return orderMap.values().stream().filter(order -> order.getBook().equals(book)).toList();
    }
}
