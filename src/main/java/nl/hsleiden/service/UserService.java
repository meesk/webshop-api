package nl.hsleiden.service;

import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Singleton;
import nl.hsleiden.model.User;
import nl.hsleiden.persistence.UserDAO;

/**
 *
 * @author Mees Kluivers
 */
@Singleton
public class UserService extends BaseService<User>
{
    private final UserDAO dao;
    
    @Inject
    public UserService(UserDAO dao) {
        this.dao = dao;
    }
    
    public Collection<User> getAll() {
        return dao.getAll();
    }
    
    public User get(int id) {
        return requireResult(dao.get(id));
    }
    
    public void add(User user) {        
        dao.add(user);
    }
    
    public void update(User authenticator, int id, User user) {
        // Controleren of deze gebruiker wel bestaat
        User oldUser = get(id);
        
        if (!authenticator.hasRole("ADMIN"))
        {
            // Vaststellen dat de geauthenticeerde gebruiker
            // zichzelf aan het aanpassen is
            assertSelf(authenticator, oldUser);
        }
        
        dao.update(id, user);
    }
    
    public void delete(int id) {
        dao.delete(id);
    }
}
