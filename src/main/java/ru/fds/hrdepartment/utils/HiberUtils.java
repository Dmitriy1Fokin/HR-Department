package ru.fds.hrdepartment.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HiberUtils {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;

    private HiberUtils() {
    }

    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hrdepartment-pu");
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }
}
