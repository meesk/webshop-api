package nl.hsleiden.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import nl.hsleiden.model.Product;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class ProductDAO {
    private final List<Product> products;
    private final Database db;

    @Inject
    public ProductDAO() {
        products = new ArrayList<>();
        db = new Database();
    }
    
    public List<Product> getAll()
    {
        products.clear();
        try{
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from product order by prod_nummer");
            Product product;
            while(rs.next()){
                product = new Product();
                product.setProdNaam(rs.getString("prod_naam"));
                product.setProdBeschrijving(rs.getString("prod_beschrijving"));
                product.setProdNummer(rs.getInt("prod_nummer"));
                product.setProdAfbeelding(rs.getString("prod_afbeelding"));
                product.setProdPrijs(rs.getDouble("prod_prijs"));
                products.add(product);
            }
            db.closeConnection(con);
            return products;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public Product get(int id)
    {
        try
        {
            return products.get(id);
        }
        catch(IndexOutOfBoundsException exception)
        {
            return null;
        }
    }
    
    public void add(Product product)
    {
        products.add(product);
    }
    
    public void update(int id, Product product)
    {
        products.set(id, product);
    }
    
    public void delete(int id)
    {
        products.remove(id);
    }
}
