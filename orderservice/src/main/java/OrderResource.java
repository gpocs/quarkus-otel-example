

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.*;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    private static final LinkedList<Order> orders = new LinkedList<>();
    
    static {
        orders.addLast(new Order("cleancode",5));
        orders.addLast(new Order("effectivejava",2));
    }

    @Inject
    @RestClient
    BookService bookService;

    private static final Logger LOG = Logger.getLogger(OrderResource.class.getName());
    @GET
    public List<Order> getAll() {
        LOG.info("Fetching all orders");
        return orders;
    }

    @POST
    public Order create(Order orderRequest) {
        Order newOrder = new Order(orderRequest.bookId, orderRequest.quantity);
        LOG.info("Creating new order: " + newOrder.bookId + ", quantity: " + newOrder.quantity);
        // Verify if the book is available
        // Check if the requested quantity is available
        Book book = bookService.getBookById(orderRequest.bookId);
        if (book.stock < orderRequest.quantity) {
            throw new BadRequestException("Requested quantity exceeds available stock");
        }
        orders.addLast(newOrder);
        orders.pollFirst(); 
        return newOrder;
    }

    @GET
    @Path("/{id}")
    public Order getById(@PathParam("id") String id) {
        LOG.info("Fetching order with ID: " + id);
        return orders.stream()
            .filter(o -> o.orderId.equals(id))
            .findFirst()
            .orElseThrow(() -> new NotFoundException("Order not found"));
    }
}
