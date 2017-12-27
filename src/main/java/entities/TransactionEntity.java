package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marcin on 2017-12-13.
 */
@Entity
public class TransactionEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long transactionNumber;

    private Long quantity;

    @ManyToMany(mappedBy = "transactions", cascade = CascadeType.PERSIST)
    private Set<Product> products = new HashSet<>();

    public TransactionEntity(Long quantity) {
        this.quantity = quantity;
    }

    public TransactionEntity() {
    }

    public Long getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(Long transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        for(Product p: products) p.getTransactions().add(this);
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.getTransactions().add(this);
    }
}
