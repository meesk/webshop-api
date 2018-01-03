package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import io.dropwizard.auth.Auth;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.hsleiden.View;
import nl.hsleiden.model.Product;
import nl.hsleiden.model.User;
import nl.hsleiden.service.ProductService;

/**
 * 
 * @author Mees Kluivers
 */
@Singleton
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource
{
    private final ProductService service;
    
    @Inject
    public ProductResource(ProductService service){
        this.service = service;
    }
    
    @GET
    @JsonView(View.Public.class)
    public Collection<Product> retrieveAll()
    {
        return service.getAll();
    }
    
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    public Product retrieve(@PathParam("id") int id)
    {
        return service.get(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void create(@Valid Product product)
    {
        service.add(product);
    }
    
//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @JsonView(View.Protected.class)
//    public void update(@PathParam("id") int id, @Auth Product authenticator, Product product)
//    {
////      service.update(authenticator, id, product);
//        System.out.println("Not implemented yet.");
//    }
//    
    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public void delete(@PathParam("id") int id)
    {
        service.delete(id);
    }
}
