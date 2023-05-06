package project.bot.util;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import project.bot.model.response.Initiator;
import project.bot.model.response.Site;
import project.bot.model.response.Tender;
import project.bot.model.response.User;

@Slf4j
public class HibernateSessionFactoryUtil {
    private static final String CLASS_ERROR = "HibernateSessionFactoryUtil error occurred: ";
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;
    }

    private static void createSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(User.class)
                         .addAnnotatedClass(Tender.class)
                         .addAnnotatedClass(Initiator.class)
                         .addAnnotatedClass(Site.class);
            StandardServiceRegistry builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            sessionFactory = configuration.buildSessionFactory(builder);
            log.info("SessionFactory is successfully created");
        } catch (HibernateException e) {
            log.error(CLASS_ERROR + e.getMessage());
        }
    }
}
