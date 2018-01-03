package nl.hsleiden.service;

import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import nl.hsleiden.model.Product;
import nl.hsleiden.model.User;
import nl.hsleiden.persistence.ProductDAO;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class ProductService extends BaseService<Product>
{
    private final ProductDAO dao;
    
    @Inject
    public ProductService(ProductDAO dao)
    {
        this.dao = dao;
    }
    
    public Collection<Product> getAll()
    {
        return dao.getAll();
    }
    
    public Product get(int id)
    {
        return requireResult(dao.get(id));
    }
    
    public void add(Product product)
    {
        
        dao.add(product);
    }
    
    public void update(User authenticator, int id, User user) {
//        // Controleren of deze gebruiker wel bestaat
//        User oldUser = get(id);
//        
//        if (!authenticator.hasRole("ADMIN"))
//        {
//            // Vaststellen dat de geauthenticeerde gebruiker
//            // zichzelf aan het aanpassen is
//            assertSelf(authenticator, oldUser);
//        }
//        
//        dao.update(id, user);
    }
    
    public void delete(int id)
    {
        // Controleren of deze gebruiker wel bestaat
        Product product = get(id);
        
        dao.delete(id);
    }
}
