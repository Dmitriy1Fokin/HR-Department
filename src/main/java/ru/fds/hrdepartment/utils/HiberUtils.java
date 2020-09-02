package ru.fds.hrdepartment.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HiberUtils {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private static final EntityManager ENTITY_MANAGER;

    private HiberUtils() {
    }

    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hrdepartment-pu");
        ENTITY_MANAGER = ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }

    public static EntityManager getEntityManager(){
        return ENTITY_MANAGER;
    }
}
