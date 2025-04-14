
import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    public String orderId;
    public String bookId;
    public int quantity;
    public LocalDateTime orderDate;
    public String status;

    public Order() {} // default constructor for JSON

    public Order(String bookId, int quantity) {
        this.orderId = UUID.randomUUID().toString();
        this.bookId = bookId;
        this.quantity = quantity;
        this.orderDate = LocalDateTime.now();
        this.status = "PENDING";
    }
}
