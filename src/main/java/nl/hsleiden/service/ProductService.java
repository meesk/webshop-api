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
    
    public Collection<Product> getCart(int id){
        return dao.getCart(id);
    }
    
    public void addCart(int prodId, int userId){
        dao.addCart(prodId, userId);
    }
        
    public void deleteCart(int prodId, int userId){
        dao.deleteCart(prodId, userId);
    }
    
    public Product get(int id)
    {
        return requireResult(dao.get(id));
    }
    
    public void add(Product product)
    {
        
        dao.add(product);
    }
    
    public void update(int id, Product product) {
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
        dao.update(id, product);
    }
    
    public void delete(int id)
    {        
        dao.delete(id);
    }
}
