package nl.hsleiden.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;
import nl.hsleiden.model.User;

/**
 *
 * @author Mees Kluivers
 */
@Singleton
public class UserDAO
{
    private final List<User> users;
    private final Database db;
    
    public UserDAO() {
        users = new ArrayList<>(); 
        db = new Database();
    }
    
    public List<User> getAll() {
        users.clear();
        try{
            Connection con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from gebruiker order by gebruiker_id");
            User user;
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("gebruiker_id"));
                user.setVoornaam(rs.getString("voornaam"));
                user.setTussenvoegsel(rs.getString("tussenvoegsel"));
                user.setAchternaam(rs.getString("achternaam"));
                user.setPostcode(rs.getString("postcode"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("wachtwoord"));
                user.setRol(rs.getString("rol"));
                users.add(user);
            }
            db.closeConnection(con);
            return users;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public User get(int id) {
        try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from gebruiker WHERE gebruiker_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            User user = new User();
            while(rs.next()){
                user.setId(rs.getInt("gebruiker_id"));
                user.setVoornaam(rs.getString("voornaam"));
                user.setTussenvoegsel(rs.getString("tussenvoegsel"));
                user.setAchternaam(rs.getString("achternaam"));
                user.setPostcode(rs.getString("postcode"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("wachtwoord"));
                user.setRol(rs.getString("rol"));
            }
            db.closeConnection(con);
            return user;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public User getByEmailAddress(String email) {
        List<User> users = getAll();
        Optional<User> result = users.stream()
            .filter(user -> user.getEmail().equals(email))
            .findAny();
        
        return result.isPresent()
            ? result.get()
            : null;
    }
    
    public void add(User user) {
        try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO gebruiker (voornaam, tussenvoegsel, achternaam, email, wachtwoord, rol, postcode) VALUES "
                    + "(?,?,?,?,?,?,?)");
            ps.setString(1, user.getVoornaam());
            ps.setString(2, user.getTussenvoegsel());
            ps.setString(3, user.getAchternaam());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRol());
            ps.setString(7, user.getPostcode());
            ps.execute();
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void update(int id, User user) {
        try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE gebruiker SET voornaam = ?, tussenvoegsel = ?, achternaam = ?, email = ?, postcode = ? WHERE gebruiker_id = ?");
            ps.setString(1, user.getVoornaam());
            ps.setString(2, user.getTussenvoegsel());
            ps.setString(3, user.getAchternaam());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPostcode());
            ps.setInt(6, id);
            ps.execute();
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void delete(int id) {
        try{
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM gebruiker WHERE gebruiker_id = ?");
            ps.setInt(1, id);
            ps.execute();
            db.closeConnection(con);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}