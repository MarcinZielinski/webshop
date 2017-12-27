import entities.*;
import org.hibernate.Session;

import javax.persistence.EntityManager;

/**
 * Created by Marcin on 2017-12-26.
 */
public class Generator {
    public static void populateDatabase(EntityManager em) {
        Supplier sup1 = new Supplier("Wedel", "Fajna 2", "Wrocław");
        Supplier sup2 = new Supplier("Hortex", "Lepsza 18", "Poznań");
        Supplier sup3 = new Supplier("PolskieGruszki", "Ciekawa 3", "Kraków");
        em.persist(sup1);
        em.persist(sup2);
        em.persist(sup3);

        Category cat1 = new Category("Batony");
        Category cat2 = new Category("Owoce");
        em.persist(cat1);
        em.persist(cat2);

        Product prod1 = new Product("Snickers", 15L);
        Product prod2 = new Product("Mars", 35L);
        Product prod3 = new Product("Twix", 42L);
        Product prod4 = new Product("Jablko", 335L);
        Product prod5 = new Product("Banan", 55L);
        Product prod6 = new Product("Gruszka", 43L);

        TransactionEntity tran1 = new TransactionEntity(50L);
        TransactionEntity tran2 = new TransactionEntity(22L);
        TransactionEntity tran3 = new TransactionEntity(12L);
        TransactionEntity tran4 = new TransactionEntity(33L);

        cat1.addProduct(prod1);
        cat1.addProduct(prod2);
        cat1.addProduct(prod3);

        cat2.addProduct(prod4);
        cat2.addProduct(prod5);
        cat2.addProduct(prod6);

        sup1.addProducts(prod1);
        sup1.addProducts(prod2);
        sup1.addProducts(prod3);
        sup2.addProducts(prod4);
        sup2.addProducts(prod5);
        sup3.addProducts(prod6);


        tran1.addProduct(prod1);
        tran1.addProduct(prod2);

        prod1.addTransaction(tran1);
        prod1.addTransaction(tran2);
        prod1.addTransaction(tran3);
        em.persist(tran1);
        em.persist(tran2);
        em.persist(tran3);
        em.persist(tran4);

        em.persist(prod1);
        em.persist(prod2);
        em.persist(prod3);
        em.persist(prod4);
        em.persist(prod5);
        em.persist(prod6);
    }

    public static void cascadeTest(Session s) {
        Category cat1 = new Category("Konserwy");
        s.save(cat1);
        Supplier sup1 = new Supplier("Kogucik","Upalna 15","Radom");
        s.save(sup1);

        Product prod1 = new Product("Paprykarz Szczeciński", 47L, sup1, cat1.getCategoryId());
        Product prod2 = new Product("Konserwa Tyrolska", 33L, sup1, cat1.getCategoryId());
        Product prod3 = new Product("Groch z kapustą", 12L, sup1, cat1.getCategoryId());

        TransactionEntity tran1 = new TransactionEntity(155L);
        TransactionEntity tran2 = new TransactionEntity(18L);
        TransactionEntity tran3 = new TransactionEntity(16L);
        TransactionEntity tran4 = new TransactionEntity(99L);


        tran1.addProduct(prod1);
        tran1.addProduct(prod2);

        prod3.addTransaction(tran2);
        prod3.addTransaction(tran3);
        prod3.addTransaction(tran4);

        s.persist(tran1);
        s.persist(prod3);
    }

    public static void populateDatabase(Session s) {
        Supplier sup1 = new Supplier("Wedel", "Fajna 2", "Wrocław");
        Supplier sup2 = new Supplier("Hortex", "Lepsza 18", "Poznań");
        Supplier sup3 = new Supplier("PolskieGruszki", "Ciekawa 3", "Kraków");
        s.save(sup1);
        s.save(sup2);
        s.save(sup3);

        Category cat1 = new Category("Batony");
        Category cat2 = new Category("Owoce");
        s.save(cat1);
        s.save(cat2);

        Product prod1 = new Product("Snickers", 15L);
        Product prod2 = new Product("Mars", 35L);
        Product prod3 = new Product("Twix", 42L);
        Product prod4 = new Product("Jablko", 335L);
        Product prod5 = new Product("Banan", 55L);
        Product prod6 = new Product("Gruszka", 43L);

        TransactionEntity tran1 = new TransactionEntity(50L);
        TransactionEntity tran2 = new TransactionEntity(22L);
        TransactionEntity tran3 = new TransactionEntity(12L);
        TransactionEntity tran4 = new TransactionEntity(33L);

        cat1.addProduct(prod1);
        cat1.addProduct(prod2);
        cat1.addProduct(prod3);

        cat2.addProduct(prod4);
        cat2.addProduct(prod5);
        cat2.addProduct(prod6);

        sup1.addProducts(prod1);
        sup1.addProducts(prod2);
        sup1.addProducts(prod3);
        sup2.addProducts(prod4);
        sup2.addProducts(prod5);
        sup3.addProducts(prod6);


        tran1.addProduct(prod1);
        tran1.addProduct(prod2);

        prod1.addTransaction(tran1);
        prod1.addTransaction(tran2);
        prod1.addTransaction(tran3);
        s.save(tran1);
        s.save(tran2);
        s.save(tran3);
        s.save(tran4);

        s.save(prod1);
        s.save(prod2);
        s.save(prod3);
        s.save(prod4);
        s.save(prod5);
        s.save(prod6);
    }

    public static void embeddedClassTest(Session s) {
        Supplier2 sup1 = new Supplier2("Wawel", new Address("Dziwna 5", "Kraków", "55-222"));
        Supplier2 sup2 = new Supplier2("Krakus", new Address("Opolska 18", "Opole", "44-444"));
        Supplier2 sup3 = new Supplier2("Magnolia", new Address("Nieciekawa 15", "Wrocław", "33-333"));
        s.save(sup1);
        s.save(sup2);
        s.save(sup3);
    }

    public static void secondaryTableTest(Session s) {
        Supplier3 sup1 = new Supplier3("Audi", "Inna 5", "Szczecin", "52-332");
        Supplier3 sup2 = new Supplier3("BMW", "Glupia 18", "Kołobrzeg", "43-344");
        Supplier3 sup3 = new Supplier3("Mercedes", "Piekna 15", "Warszawa", "77-737");
        s.save(sup1);
        s.save(sup2);
        s.save(sup3);
    }
}
