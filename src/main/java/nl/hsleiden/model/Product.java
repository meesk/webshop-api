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
 * @author Peter van Vliet
 */
public class Product
{
    
    @JsonView(View.Public.class)
    private Integer prodNummer;
    
    @NotEmpty
    @Length(min = 3, max = 50)
    @JsonView(View.Public.class)
    private String prodNaam;
    
    @NotEmpty
    @Length(min = 6)
    @JsonView(View.Public.class)
    private String prodBeschrijving;
    
    @NotNull
    @JsonView(View.Public.class)
    private Double prodPrijs;
    
    @JsonView(View.Public.class)
    private String prodAfbeelding;
    
    @JsonView(View.Public.class)
    private String prodBedrijf;

    public String getProdAfbeelding() {
        return prodAfbeelding;
    }

    public void setProdAfbeelding(String prodAfbeelding) {
        this.prodAfbeelding = prodAfbeelding;
    }

    
    public Integer getProdNummer() {
        return prodNummer;
    }

    public void setProdNummer(Integer prodNummer) {
        this.prodNummer = prodNummer;
    }

    public String getProdNaam() {
        return prodNaam;
    }

    public void setProdNaam(String prodNaam) {
        this.prodNaam = prodNaam;
    }

    public String getProdBeschrijving() {
        return prodBeschrijving;
    }

    public void setProdBeschrijving(String prodBeschrijving) {
        this.prodBeschrijving = prodBeschrijving;
    }

    public Double getProdPrijs() {
        return prodPrijs;
    }

    public void setProdPrijs(Double prodPrijs) {
        this.prodPrijs = prodPrijs;
    }

    public String getProdBedrijf() {
        return prodBedrijf;
    }

    public void setProdBedrijf(String prodBedrijf) {
        this.prodBedrijf = prodBedrijf;
    }

}
