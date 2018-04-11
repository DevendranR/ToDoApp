package com.dev.resources;

import org.apache.log4j.Logger;

import org.apache.log4j.xml.DOMConfigurator;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtility {

	private static final String CONFIGURATION_LOCATION = "com/dev/resources/hibernate.cfg.xml";
	private static SessionFactory sessionFactory = getSessionFactory();

	private static SessionFactory getSessionFactory() {
		try {
			if (sessionFactory==null) {

				// Step1 : Loading the configuration details from hibernate.cfg.xml
				Configuration configuration = new Configuration().configure(CONFIGURATION_LOCATION);

				// Step2 : Creating ServiceRegistry using the StandardServiceRegistryBuilder and Configuration defined in Step1

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

				// Step3 : Creating the SessionFactory using the Configuration and serviceRegistry
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			}
		} catch (Exception e) {
			DOMConfigurator.configure("src/com/dev/resources/log4j.xml");
			Logger logger=Logger.getLogger(HibernateUtility.class);
			logger.debug(e.getMessage(),e);
			throw e;
		}
		return sessionFactory;
	}

	public static SessionFactory createSessionFactory() {
		return getSessionFactory();
	}

	public static void closeSessionFactory() {
		if(!sessionFactory.isClosed()){
			sessionFactory.close();
		}
	}
}
