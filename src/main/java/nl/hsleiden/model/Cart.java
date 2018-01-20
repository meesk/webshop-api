package nl.hsleiden.model;

import com.fasterxml.jackson.annotation.JsonView;
import javax.validation.constraints.NotNull;
import nl.hsleiden.View;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Meer informatie over validatie:
 *  http://hibernate.org/validator/
 * 
 * @author Mees Kluivers
 */
public class Cart
{
    @NotNull
    @JsonView(View.Public.class)
    private Integer prodNummer;
    
    @NotNull
    @JsonView(View.Public.class)
    private Integer userId;

    public Integer getProdNummer() {
        return prodNummer;
    }

    public void setProdNummer(Integer prodNummer) {
        this.prodNummer = prodNummer;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    
}
