import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/books")
@RegisterRestClient(configKey = "books-api")
public interface BookService {

    @GET
    @Path("/{id}")
    Book getBookById(@PathParam("id") String id);
}
