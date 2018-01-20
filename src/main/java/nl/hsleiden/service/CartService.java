package nl.hsleiden.service;

import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import nl.hsleiden.model.Product;
import nl.hsleiden.model.User;
import nl.hsleiden.persistence.CartDAO;

/**
 *
 * @author Mees Kluivers
 */
@Singleton
public class CartService extends BaseService<User>
{
    private final CartDAO dao;
    
    @Inject
    public CartService(CartDAO dao) {
        this.dao = dao;
    }
    
    public Collection<Product> getCart(int id) {
        return dao.getCart(id);
    }
    
    public void addCart(int prodId, int userId) {        
        dao.addCart(prodId, userId);
    }
    
    public void deleteCart(int prodId, int userId) {
        dao.deleteCart(prodId, userId);
    }
    
    public void updateCart(int prodId, int userId) {
        dao.updateCart(prodId, userId);
    }
}
