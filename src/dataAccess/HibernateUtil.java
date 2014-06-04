package dataAccess;

import java.io.IOException;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static final ThreadLocal session = new ThreadLocal();
	
	static {
        try {
        	Configuration config = new Configuration().configure("/dataAccess/hibernate.cfg.xml");
        	ServiceRegistry service = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

        	sessionFactory = config.buildSessionFactory(service);
        } catch (Throwable exception) {
            System.out.println("Problem creating session factory");
            System.out.println(exception);
        }
    }
	
	@SuppressWarnings("unchecked")
	public static Session getCurrentSession(){
		Session sesion = (Session) session.get();
		if(sesion==null || !sesion.isOpen()){
			sesion = sessionFactory.openSession();
		}
		session.set(sesion);
		return sesion;
	}
	
	@SuppressWarnings({ "null", "unchecked" })
	public static void closeSession(){
		Session s=(Session)session.get();
		if(s != null || s.isOpen()){
                    s.close();
		}
		session.set(s);
	}
	
}