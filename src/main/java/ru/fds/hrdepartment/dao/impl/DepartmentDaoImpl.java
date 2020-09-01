package ru.fds.hrdepartment.dao.impl;

import org.springframework.stereotype.Component;
import ru.fds.hrdepartment.dao.DepartmentDao;
import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.utils.HiberUtils;

import javax.persistence.EntityManager;
import java.util.Optional;

@Component
public class DepartmentDaoImpl implements DepartmentDao {

    @Override
    public Optional<Department> findById(Long departmentId){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        return Optional.ofNullable(entityManager.find(Department.class, departmentId));
    }

    @Override
    public Department save(Department department){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(department);
        entityManager.getTransaction().commit();
        return department;
    }

    @Override
    public Department update(Department department){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(department);
        entityManager.getTransaction().commit();
        return department;
    }
}
