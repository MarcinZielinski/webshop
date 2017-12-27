import entities.Product;
import entities.TransactionEntity;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
/**
 * Created by Marcin on 2017-12-12.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        try(final Session session = getSession()) {
            Transaction tx = session.beginTransaction();
            Generator.populateDatabase(session);
            tx.commit();
        }

        get("/hello", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "/buying-layout.vm")
            );
        });

        post("/buy", (request, response) -> {
            String a, b;
            a = request.queryParams("quantity");
            b = request.queryParams("productId");
            Map<String, Object> model = new HashMap<>();

            if(buyProduct(Long.parseLong(a),Long.parseLong(b)))
                return new VelocityTemplateEngine().render(
                        new ModelAndView(model, "/bought-layout.vm"));
            else
                return new VelocityTemplateEngine().render(
                        new ModelAndView(model, "/failed-to-bought-layout.vm"));
        });
    }

    private static Boolean buyProduct(Long quantity, Long productId) {
        Boolean res;
        try(final Session session = getSession()) {
            Transaction tx = session.beginTransaction();
            Product product = session.get(Product.class, productId);
            res = product != null;
            if(res) {
                TransactionEntity transactionEntity = new TransactionEntity(quantity);
                transactionEntity.addProduct(product);
                session.persist(transactionEntity);
            }
            tx.commit();
        }
        return res;
    }

    public static void JPAmain(final String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDatabaseConfig");
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Generator.populateDatabase(em);
        etx.commit();
        em.close();
    }
}