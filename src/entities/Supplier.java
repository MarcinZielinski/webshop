package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marcin on 2017-12-13.
 */
@Entity
public class Supplier extends Company {

    private Long bankAccountNumber;

    @OneToMany(mappedBy = "supplier")
    private Set<Product> products = new HashSet<>();

    public Supplier(String companyName, String street, String city, String zipcode, Long bankAccountNumber) {
        super(companyName, street, city, zipcode);
        this.bankAccountNumber = bankAccountNumber;
    }

    public Long getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(Long bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
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
        product.setSupplier(this);
    }

    public Supplier() {
    }
}
