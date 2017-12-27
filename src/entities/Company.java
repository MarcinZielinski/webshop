package entities;

import javax.persistence.*;

/**
 * Created by Marcin on 2017-12-27.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String companyName;
    private String street;
    private String city;
    private String zipdode;

    public Company() {}

    public Company(String companyName, String street, String city, String zipdode) {
        this.companyName = companyName;
        this.street = street;
        this.city = city;
        this.zipdode = zipdode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipdode() {
        return zipdode;
    }

    public void setZipdode(String zipdode) {
        this.zipdode = zipdode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
