package caua;

import caua.model.Book;
import caua.model.Client;
import caua.model.Order;
import caua.repository.*;
import caua.service.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ClientRepository clientRepository = new ClientInMemory();
        BookRepository bookRepository = new BookInMemory();
        OrderRepository orderRepository = new OrderInMemory();

        ClientService clientService = new ClientService(clientRepository);
        BookService bookService = new BookService(bookRepository);
        OrderService orderService = new OrderService(orderRepository);

        while (true) {

            System.out.println("\n=== LIBRARY SYSTEM ===");
            System.out.println("1 - Create Client");
            System.out.println("2 - Create Book");
            System.out.println("3 - Create Order");
            System.out.println("4 - Confirm Borrow");
            System.out.println("5 - Cancel Borrow");
            System.out.println("6 - List Orders");
            System.out.println("0 - Exit");

            System.out.print("Option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (option) {

                    case 1 -> {

                        System.out.print("Client name: ");
                        String name = scanner.nextLine();

                        Client client = clientService.createClient(name);

                        System.out.println("Client created with ID: " + client.getId());
                    }

                    case 2 -> {

                        System.out.print("Book title: ");
                        String title = scanner.nextLine();

                        Book book = bookService.createBook(title);

                        System.out.println("Book created with ID: " + book.getId());
                    }

                    case 3 -> {

                        System.out.print("Client ID: ");
                        Long clientId = scanner.nextLong();

                        System.out.print("Book ID: ");
                        Long bookId = scanner.nextLong();
                        scanner.nextLine();

                        Client client = clientService.findById(clientId);
                        Book book = bookService.findByIdBook(bookId);

                        Order order = orderService.createOrder(client, book);

                        System.out.println("Order created with ID: " + order.getId());
                    }

                    case 4 -> {

                        System.out.print("Order ID: ");
                        Long orderId = scanner.nextLong();
                        scanner.nextLine();

                        orderService.confirmBorrow(orderId);

                        System.out.println("Borrow confirmed.");
                    }

                    case 5 -> {

                        System.out.print("Order ID: ");
                        Long orderId = scanner.nextLong();
                        scanner.nextLine();

                        orderService.cancelBorrow(orderId);

                        System.out.println("Borrow canceled.");
                    }

                    case 6 -> {

                        List<Order> orders = orderService.findAllOrders();

                        if (orders.isEmpty()) {
                            System.out.println("No orders found.");
                        } else {

                            System.out.println("\n=== ORDERS ===");

                            for (Order order : orders) {

                                System.out.println(
                                        "Order ID: " + order.getId() +
                                                " | Client: " + order.getClient().getName() +
                                                " | Book: " + order.getBook().getTitle() +
                                                " | Status: " + order.getStatus()
                                );
                            }
                        }
                    }

                    case 0 -> {
                        System.out.println("Exiting system...");
                        return;
                    }

                    default -> System.out.println("Invalid option.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}