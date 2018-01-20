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
 * @author Mees Kluivers
 */
@Singleton
public class CartDAO {
    private final List<Product> products;
    private final Database db;

    @Inject
    public CartDAO() {
        products = new ArrayList<>();
        db = new Database();
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
    
    public void updateCart(int prodId, int userId){
         try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE cart SET product_id = ? WHERE gebruiker_id = ?");
            ps.setInt(1, prodId);
            ps.setInt(2, userId);
            ps.execute();
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        } 
    }
    
    

}
