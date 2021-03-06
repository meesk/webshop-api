package nl.hsleiden.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;

import io.dropwizard.auth.Auth;

import java.util.Collection;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.server.Authentication.User;

import nl.hsleiden.View;
import nl.hsleiden.model.Product;
import nl.hsleiden.service.CartService;

/**
 * Meer informatie over resources:
 *  https://jersey.java.net/documentation/latest/user-guide.html#jaxrs-resources
 * 
 * @author Mees Kluivers
 */
@Singleton
@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {
    private final CartService service;
    
    @Inject
    public CartResource(CartService service) {
        this.service = service;
    }
    @GET
    @Path("/user/{id}")
    @RolesAllowed("GUEST")
    @JsonView(View.Protected.class)
    public Collection<Product> getCart(@PathParam("id") int id, @Auth User authenticator) {
        return service.getCart(id);
    }
    
    @POST
    @Path("/user/{user_id}/product/{product_id}")
    @JsonView(View.Protected.class)
    public void addCart(@PathParam("product_id") int prodId, @PathParam("user_id") int userId) {
        service.addCart(prodId, userId);
    }
    
    @DELETE
    @Path("/user/{user_id}/product/{id}")
    @JsonView(View.Protected.class)
    public void deleteCart(@PathParam("id") int prodId, @PathParam("user_id") int userId) {
        service.deleteCart(prodId, userId);
    }
    
    @DELETE
    @Path("/user/{id}")
    @RolesAllowed("GUEST")
    @JsonView(View.Protected.class)
    public void deleteAll(@PathParam("id") int userId, @Auth User authenticator) {
         service.deleteAll(userId);
    }
    
    @PUT
    @Path("/user/{user_id}/product/{product_id}")
    @JsonView(View.Protected.class)
    public void updateCart(@PathParam("user_id") int userId, @PathParam("product_id") int prodId, @Auth User authenticator) { 
    
    }
    
}
