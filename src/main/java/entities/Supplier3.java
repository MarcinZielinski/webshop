package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marcin on 2017-12-27.
 */
@Entity
@SecondaryTable(name="ADDRESS_TBL")
public class Supplier3 {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String companyName;
    @Column(table = "ADDRESS_TBL")
    private String street;
    @Column(table = "ADDRESS_TBL")
    private String city;
    @Column(table = "ADDRESS_TBL")
    private String zipcode;

    @OneToMany(mappedBy = "supplier")
    private Set<Product> products;

    public Supplier3(String companyName, String street, String city, String zipcode) {
        this.companyName = companyName;
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        products = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProducts(Product product) {
        if(products == null) products = new HashSet<>();
        products.add(product);
    }

    public Supplier3() {
    }
}
