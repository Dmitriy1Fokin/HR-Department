package ru.fds.hrdepartment.dao.impl;

import org.springframework.stereotype.Component;
import ru.fds.hrdepartment.dao.SickLeaveDao;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.SickLeave;
import ru.fds.hrdepartment.utils.HiberUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Component
public class SickLeaveDaoImpl implements SickLeaveDao {

    private final EntityManager entityManager = HiberUtils.getEntityManager();

    @Override
    public Optional<SickLeave> findById(Long sickLeaveId){
        return Optional.ofNullable(entityManager.find(SickLeave.class, sickLeaveId));
    }

    @Override
    public List<SickLeave> findAllByEmployee(Employee employee){
        return entityManager
                .createQuery("select sl from SickLeave sl where sl.employee = :employee", SickLeave.class)
                .setParameter("employee", employee)
                .getResultList();
    }

    @Override
    public SickLeave save(SickLeave sickLeave){
        entityManager.getTransaction().begin();
        entityManager.persist(sickLeave);
        entityManager.getTransaction().commit();
        return sickLeave;
    }

    @Override
    public SickLeave update(SickLeave sickLeave){
        entityManager.getTransaction().begin();
        entityManager.merge(sickLeave);
        entityManager.getTransaction().commit();
        return sickLeave;
    }

    @Override
    public void deleteAllByEmployee(Employee employee){
        entityManager.getTransaction().begin();
        entityManager
                .createQuery("select sl from SickLeave sl where sl.employee = :employee", SickLeave.class)
                .setParameter("employee", employee)
                .getResultList().forEach(entityManager::remove);
        entityManager.getTransaction().commit();
    }
}
