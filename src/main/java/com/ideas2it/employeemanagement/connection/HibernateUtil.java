/*
 * Copyright (c) ideas2it.
 *
 * Date: 2021-09-12
 */
package com.ideas2it.employeemanagement.connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * <h1> Employee database connection</h1>
 * Establishing  a connection with a database 
 *
 * @author	Dinesh Kumar
 * @version	1.0
 * @since   2021-08-27
 * 
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    
    private HibernateUtil() {
    }
   
    /**
     * Creating session factory for the database connection
     *
     * @return session factory
     */
    public static SessionFactory getSessionFactory() {
        if (null == sessionFactory) {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable e) {
            	System.out.println("connection created");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
