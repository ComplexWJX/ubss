package com.itany.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
//import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	private static ThreadLocal<Session> threadLocal = new ThreadLocal<>();

	static {
		try {
			/*Configuration configuration = new Configuration();
			configuration.configure();
			ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(sr);*/

			//v5.3

			BootstrapServiceRegistryBuilder bootstrapRegistryBuilder =
					new BootstrapServiceRegistryBuilder();
            // add a custom ClassLoader
			bootstrapRegistryBuilder.applyClassLoader(HibernateUtil.class.getClassLoader());
             // manually add an Integrator
//			bootstrapRegistryBuilder.applyIntegrator( customIntegrator );

			BootstrapServiceRegistry bootstrapRegistry = bootstrapRegistryBuilder.build();


			Configuration configuration = new Configuration();
			configuration.configure();
			sessionFactory = configuration.buildSessionFactory(bootstrapRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Session getSession() {
		Session session = threadLocal.get();
		if (session == null || !session.isOpen()) {
			session = sessionFactory == null ? null : sessionFactory.openSession();
			threadLocal.set(session);
		}

		return session;
	}

	public static void closeSession() {
		Session session = threadLocal.get();
		// threadLocal.remove();
		threadLocal.set(null);
		if (session != null) {
			session.close();
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
