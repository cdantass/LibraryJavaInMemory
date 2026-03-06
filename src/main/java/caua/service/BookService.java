package caua.service;

import caua.model.Book;
import caua.repository.BookRepository;

import java.util.List;
import java.util.Optional;

public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book createBook(String title){

        Book book = new Book(null, title);
        return bookRepository.save(book);
    }

    public Book findByIdBook(Long id){
        return bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
    }

    public Book findByTitleBooks(String title){
        return bookRepository.findByTitle(title).orElseThrow(()-> new RuntimeException("Title not found"));
    }

    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }
}