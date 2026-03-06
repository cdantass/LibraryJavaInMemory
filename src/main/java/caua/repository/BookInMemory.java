package caua.repository;

import caua.model.Book;

import java.util.*;

public class BookInMemory implements BookRepository{

    private final Map<Long, Book> books = new HashMap<>();

    @Override
    public void save(Book book){
        books.put(book.getId(), book);
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
