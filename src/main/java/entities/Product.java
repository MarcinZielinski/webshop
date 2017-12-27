package entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Marcin on 2017-12-12.
 */
@Entity
public class Product {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String productName;
    private Long unitsOnStock;

    @ManyToOne
    @JoinColumn(name = "SUPPLIER_ID")
    private Supplier supplier;

    public Product() {}

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<TransactionEntity> transactions = new HashSet<>();

    @Column(name ="CATEGORY_ID")
    private int categoryId;

    public Product(String productName, Long unitsOnStock) {
        this.productName = productName;
        this.unitsOnStock = unitsOnStock;
    }

    public Product(String productName, Long unitsOnStock, Supplier supplier, int categoryId) {
        this.productName = productName;
        this.unitsOnStock = unitsOnStock;
        this.supplier = supplier;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        supplier.getProducts().add(this);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getUnitsOnStock() {
        return unitsOnStock;
    }

    public void setUnitsOnStock(Long unitsOnStock) {
        this.unitsOnStock = unitsOnStock;
    }

    public Set<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TransactionEntity> transactions) {
        for(TransactionEntity t: transactions) t.getProducts().add(this);
        this.transactions = transactions;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void addTransaction(TransactionEntity transaction) {
        transactions.add(transaction);
        transaction.getProducts().add(this);
    }
}
