package nl.hsleiden.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO product (prod_naam, prod_prijs, prod_beschrijving, prod_afbeelding) VALUES"
                    + " (?,?,?,?)");
            ps.setString(1,product.getProdNaam());
            ps.setDouble(2,product.getProdPrijs());
            ps.setString(3, product.getProdBeschrijving());
            ps.setString(4,product.getProdAfbeelding());
            ps.execute();
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void update(int id, Product product)
    {
                try{
                Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE product SET prod_naam = ?, prod_prijs = ?, prod_beschrijving = ?, prod_afbeelding = ? WHERE prod_nummer = ?");
            ps.setString(1,product.getProdNaam());
            ps.setDouble(2, product.getProdPrijs());
            ps.setString(3, product.getProdBeschrijving());
            ps.setString(4, product.getProdAfbeelding());
            ps.setInt(5, id);
            ps.execute();
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public List<Product> getCart(int id){
       List<Product> productsInCart = new ArrayList<Product>();
         try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT p.* FROM product p JOIN cart c ON c.product_id = p.prod_nummer JOIN gebruiker g ON c.gebruiker_id = g.gebruiker_id WHERE g.gebruiker_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Product product;
            while(rs.next()){
                product = new Product();
                product.setProdNaam(rs.getString("prod_naam"));
                product.setProdBeschrijving(rs.getString("prod_beschrijving"));
                product.setProdNummer(rs.getInt("prod_nummer"));
                product.setProdAfbeelding(rs.getString("prod_afbeelding"));
                product.setProdPrijs(rs.getDouble("prod_prijs"));
                productsInCart.add(product);
            }
            db.closeConnection(con);
            return productsInCart;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        } 
    }
    
    public void addCart(int prodId, int userId){
         try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO cart (product_id, gebruiker_id) VALUES (?,?);");
            ps.setInt(1, prodId);
            ps.setInt(2, userId);
            ps.execute();
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        } 
    }
        
    public void deleteCart(int prodId, int userId){
         try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM cart WHERE product_id = ? AND gebruiker_id = ? limit 1");
            ps.setInt(1, prodId);
            ps.setInt(2, userId);
            ps.execute();
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        } 
    }
    
    public void delete(int id)
    {
        try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM product WHERE prod_nummer = ?");
            ps.setInt(1,id);
            ps.execute();
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
