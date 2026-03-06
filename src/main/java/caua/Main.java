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

        while (true){

            System.out.println("\n=== LIBRARY SYSTEM ===");
            System.out.println("1 - Create Client");
            System.out.println("2 - Create Book");
            System.out.println("3 - Create Order");
            System.out.println("4 - Confirm Borrow");
            System.out.println("5 - Cancel Borrow");
            System.out.println("6 - List Orders");
            System.out.println("0 - Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (option){

                    case 1 -> {
                        System.out.print("Client ID: ");
                        Long id = scanner.nextLong();
                        scanner.nextLine();

                        System.out.print("Client name: ");
                        String name = scanner.nextLine();

                        clientService.createClient(id, name);

                        System.out.println("Client created.");
                    }

                    case 2 -> {
                        System.out.print("Book ID: ");
                        Long id = scanner.nextLong();
                        scanner.nextLine();

                        System.out.print("Book title: ");
                        String title = scanner.nextLine();

                        bookService.createBook(id, title);

                        System.out.println("Book created.");
                    }

                    case 3 -> {
                        System.out.print("Order ID: ");
                        Long orderId = scanner.nextLong();

                        System.out.print("Client ID: ");
                        Long clientId = scanner.nextLong();

                        System.out.print("Book ID: ");
                        Long bookId = scanner.nextLong();

                        Client client = clientService.findById(clientId);
                        Book book = bookService.findByIdBook(bookId);

                        orderService.createOrder(orderId, client, book);

                        System.out.println("Order created.");
                    }

                    case 4 -> {
                        System.out.print("Order ID: ");
                        Long orderId = scanner.nextLong();

                        orderService.confirmBorrow(orderId);

                        System.out.println("Borrow confirmed.");
                    }

                    case 5 -> {
                        System.out.print("Order ID: ");
                        Long orderId = scanner.nextLong();

                        orderService.cancelBorrow(orderId);

                        System.out.println("Borrow canceled.");
                    }

                    case 6 -> {

                        List<Order> orders = orderService.findAllOrders();

                        for (Order order : orders){
                            System.out.println(
                                    "Order ID: " + order.getId() +
                                            " | Client: " + order.getClient().getName() +
                                            " | Book: " + order.getBook().getTitle() +
                                            " | Status: " + order.getStatus()
                            );
                        }
                    }

                    case 0 -> {
                        System.out.println("Exiting...");
                        return;
                    }

                    default -> System.out.println("Invalid option");
                }

            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}