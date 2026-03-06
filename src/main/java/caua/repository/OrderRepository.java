package caua.repository;

import caua.model.Book;
import caua.model.Order;
import caua.model.StatusOrder;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    void save(Order order);

    Optional<Order> findById(Long id);

    Optional<Order> findByStatus(StatusOrder status);

    List<Order> findByBook(Book book);

    List<Order> findAll();
}