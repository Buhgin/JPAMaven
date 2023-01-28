package org.example.username.com.da.jpa.entite.manger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ThisEntity {
    private static EntityManagerFactory entityManagerFactory = createEntityManagerFactory();

    private static EntityManager entityManager = thisCreateEntityManager();

    public static EntityManagerFactory createEntityManagerFactory() {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("main");

            return entityManagerFactory;
        } catch (Exception ex) {
            System.err.println("Initial EntityManager  Factory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager thisCreateEntityManager() {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager;
        } catch (Exception ex) {
            System.err.println("Initial EntityManager creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            createEntityManagerFactory();
        }
        return entityManagerFactory;
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            thisCreateEntityManager();
        }
        return entityManager;
    }
    public static void shutdownEntityManagerFactory() {
        getEntityManagerFactory().close();
    }
    public static void shutdownEntityManager(){
        getEntityManager().close();
    }
}