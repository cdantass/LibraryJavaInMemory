package caua.repository;

import caua.model.Book;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class BookInMemory implements BookRepository{

    private final Map<Long, Book> books = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Book save(Book book){
        if(book.getId() == null){
            Long id = idGenerator.getAndIncrement();
            book.setId(id);
        }

        books.put(book.getId(), book);

        return book;
    }

    @Override
    public List<Book> findAll(){
        return new ArrayList<>(books.values());
    }

    @Override
    public Optional<Book> findById(Long id){
        return Optional.ofNullable(books.get(id));    }

    @Override
    public Optional<Book> findByTitle(String title){
        return books.values().stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).findFirst();
    }
}
