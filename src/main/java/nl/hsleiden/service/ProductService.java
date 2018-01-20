package nl.hsleiden.service;

import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import nl.hsleiden.model.Product;
import nl.hsleiden.model.User;
import nl.hsleiden.persistence.ProductDAO;

/**
 *
 * @author Mees Kluivers
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
    
    public void update(int id, Product product) {
        dao.update(id, product);
    }
    
    public void delete(int id)
    {        
        dao.delete(id);
    }
}
