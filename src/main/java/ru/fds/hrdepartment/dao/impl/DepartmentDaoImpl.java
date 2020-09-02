package ru.fds.hrdepartment.dao.impl;

import org.springframework.stereotype.Component;
import ru.fds.hrdepartment.dao.DepartmentDao;
import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.utils.HiberUtils;

import javax.persistence.EntityManager;
import java.util.Optional;

@Component
public class DepartmentDaoImpl implements DepartmentDao {

    private final EntityManager entityManager = HiberUtils.getEntityManager();

    @Override
    public Optional<Department> findById(Long departmentId){
        return Optional.ofNullable(entityManager.find(Department.class, departmentId));
    }

    @Override
    public Department save(Department department){
        entityManager.getTransaction().begin();
        entityManager.persist(department);
        entityManager.getTransaction().commit();
        return department;
    }

    @Override
    public Department update(Department department){
        entityManager.getTransaction().begin();
        entityManager.merge(department);
        entityManager.getTransaction().commit();
        return department;
    }
}
