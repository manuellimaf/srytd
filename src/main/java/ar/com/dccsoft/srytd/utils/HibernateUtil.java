package ar.com.dccsoft.srytd.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
	// Logger createion MUST be the first statement (NPE Otherwise)
	private static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	public static void init() {
		logger.info("Hibernate initialization finished");
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
    private static SessionFactory buildSessionFactory() {
		logger.info("Initializing Hibernate");
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	Configuration configuration = new Configuration().configure("hibernate/hibernate.cfg.xml");
        
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            return configuration.buildSessionFactory(serviceRegistry);
        
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
    		logger.error("Initial SessionFactory creation failed", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

}