package com.revature.utilities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
		
		
		/*
		 * Configuration is used as it creates SessionFactories.
		 * */
		private static Session sess;
		private static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		
		/*
		 * This is a singleton design pattern!
		 * */
		public static Session getSession() {
			if(sess==null) {
				sess = sf.openSession();
			} 
			return sess;
		}
		
		public static void closeSess() {
			sess.close();
			sess = null;
		}
		
		
		
	}