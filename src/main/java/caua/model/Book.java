package caua.model;

public class Book {

    private Long id;
    private String title;

    public Book(Long id, String title){
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
