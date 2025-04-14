import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.*;
import org.jboss.logging.Logger;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    private static final Map<String, Book> books = new HashMap<>();
    private static final Logger LOG = Logger.getLogger(BookResource.class.getName());

    static {
        // Preload some dummy data
        books.put("cleancode", new Book("cleancode", "Clean Code", "Robert C. Martin", 39.99, 10));
        books.put("effectivejava", new Book("effectivejava", "Effective Java", "Joshua Bloch", 45.00, 5));
    }

    @GET
    public Collection<Book> getAll() {
        LOG.info("Fetching all books");
        return books.values();
    }

    @GET
    @Path("/{id}")
    public Book getById(@PathParam("id") String id) {
        Book book = books.get(id);
        LOG.info("Fetching book with ID: " + id);
        if (book == null) {
            throw new NotFoundException("Book not found");
        }
        return book;
    }

    @POST
    public Book create(Book book) {
        LOG.info("Creating new book: " + book.title);
        String newId = UUID.randomUUID().toString();
        book.id = newId;
        books.put(newId, book);
        return book;
    }

    @PUT
    @Path("/{id}/stock")
    public Book updateStock(@PathParam("id") String id, @QueryParam("amount") int amount) {
        LOG.info("Updating stock for book with ID: " + id + ", amount: " + amount);
        Book book = books.get(id);
        if (book == null) {
            throw new NotFoundException("Book not found");
        }
        book.stock += amount;
        return book;
    }
}
