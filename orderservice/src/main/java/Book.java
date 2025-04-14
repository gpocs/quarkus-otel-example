
public class Book {
    public String id;
    public String title;
    public String author;
    public double price;
    public int stock;

    public Book() {} // Needed for JSON serialization

    public Book(String id, String title, String author, double price, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }
}
