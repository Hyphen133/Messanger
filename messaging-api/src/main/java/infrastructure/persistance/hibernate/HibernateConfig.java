package infrastructure.persistance.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;

public class HibernateConfig {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            createSessionFactory();
        }
        return sessionFactory;
    }

    private static void createSessionFactory() {
        final Properties properties = new Properties();
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.URL, "jdbc:postgresql://localhost:7777/messaging");
        properties.put(Environment.USER, "messenger_user");
        properties.put(Environment.PASS, "password");
        properties.put(Environment.HBM2DDL_AUTO, "create");

        final Configuration configuration = new Configuration();
        configuration.setProperties(properties);

        configuration.addAnnotatedClass(MessageRecord.class);
        configuration.addAnnotatedClass(ChatRecord.class);
        configuration.addAnnotatedClass(UserRecord.class);
        configuration.addAnnotatedClass(ChatUsersRecord.class);

        final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    }

}
