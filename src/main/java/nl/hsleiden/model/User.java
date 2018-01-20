package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.security.Principal;
import javax.validation.constraints.NotNull;
import nl.hsleiden.View;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Meer informatie over validatie:
 *  http://hibernate.org/validator/
 * 
 * @author Mees Kluivers
 */
public class User implements Principal
{
    
    private Integer id;
    
    // @NotEmpty
    @Length(min = 3, max = 100)
    @JsonView(View.Public.class)
    private String voornaam;
    
    @Length(max = 100)
    @JsonView(View.Public.class)
    private String tussenvoegsel;
    
    @Length(min = 3, max = 100)
    @JsonView(View.Public.class)
    private String achternaam;
    
    // @NotEmpty
    @Length(min = 4, max = 7)
    @JsonView(View.Public.class)
    private String postcode;

    
    // @NotEmpty
    @Email
    @JsonView(View.Public.class)
    private String email;
    
    // @NotEmpty
    @Length(min = 4)
    @JsonView(View.Protected.class)
    private String password;
    
    @JsonView(View.Public.class)
    private String rol;
    
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean hasRole(String role){
        if(rol != null){
            if(role.equals(this.rol)){
                return true;
            }
        }
        return true;
    }

    @Override
    @JsonIgnore
    public String getName() {
        return voornaam;
    }
    
    public boolean equals(User user) {
        return email.equals(user.getEmail());
    }
}