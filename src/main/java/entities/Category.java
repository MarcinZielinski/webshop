package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 2017-12-13.
 */
@Entity
public class Category {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int categoryId;

    private String name;

    @OneToMany(mappedBy = "categoryId")
    private List<Product> products;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        for (Product p : products) p.setCategoryId(categoryId);
        this.products = products;
    }

    public void addProduct(Product product) {
        if(products == null) products = new ArrayList<>();
        products.add(product);
        product.setCategoryId(categoryId);
    }
}
