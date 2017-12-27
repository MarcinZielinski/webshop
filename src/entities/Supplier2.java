package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marcin on 2017-12-27.
 */
@Entity
public class Supplier2 {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String companyName;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "supplier")
    private Set<Product> products;

    public Supplier2() {}

    public Supplier2(String companyName, Address address) {
        this.companyName = companyName;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
}
