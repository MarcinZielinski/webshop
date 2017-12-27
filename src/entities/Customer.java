package entities;

import javax.persistence.*;

/**
 * Created by Marcin on 2017-12-27.
 */
@Entity
public class Customer extends Company{

    private float discount;

    public Customer(){}

    public Customer(float discount) {
        this.discount = discount;
    }

    public Customer(String companyName, String street, String city, String zipCode, float discount) {
        super(companyName, street, city, zipCode);
        this.discount = discount;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
