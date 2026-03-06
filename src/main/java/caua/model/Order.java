package caua.model;

public class Order {

    private Long id;
    private Client client;
    private Book book;
    private StatusOrder status;

    public Order(Long id, Client client, Book book){
        this.id = id;
        this.client = client;
        this.book = book;
        this.status = StatusOrder.PROCESS;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Book getBook() {
        return book;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void borrow(){
        if (this.status.equals(StatusOrder.CONFIRMED)){
            throw new IllegalStateException("Borrow is confirmed");
        }
        this.status = StatusOrder.CONFIRMED;
    }

    public void cancelBorrow(){
        if (this.status.equals(StatusOrder.CANCELED)){
            throw new IllegalStateException("Borrow not confirmed");
        }
        this.status = StatusOrder.CANCELED;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
