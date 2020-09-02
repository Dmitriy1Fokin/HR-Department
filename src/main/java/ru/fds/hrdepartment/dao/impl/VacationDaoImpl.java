package ru.fds.hrdepartment.dao.impl;

import org.springframework.stereotype.Component;
import ru.fds.hrdepartment.dao.VacationDao;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.Vacation;
import ru.fds.hrdepartment.utils.HiberUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Component
public class VacationDaoImpl implements VacationDao {

    private final EntityManager entityManager = HiberUtils.getEntityManager();
    private static final String PARAMETER_EMP = "employee";

    @Override
    public Optional<Vacation> findById(Long vacationId){
        return Optional.ofNullable(entityManager.find(Vacation.class, vacationId));
    }

    @Override
    public List<Vacation> findAllByEmployee(Employee employee){
        return entityManager
                .createQuery("select v from Vacation v where v.employee = :employee", Vacation.class)
                .setParameter(PARAMETER_EMP, employee)
                .getResultList();
    }

    @Override
    public Optional<Vacation> findLastVacationByEmployee(Employee employee){
        List<Vacation> vacations = entityManager
                .createQuery("select v from Vacation v where v.employee = :employee order by v.dateStart desc ", Vacation.class)
                .setParameter(PARAMETER_EMP, employee)
                .setMaxResults(1)
                .getResultList();

        return vacations.isEmpty() ? Optional.empty() : Optional.ofNullable(vacations.get(0));
    }

    @Override
    public Vacation save(Vacation vacation){
        entityManager.getTransaction().begin();
        entityManager.persist(vacation);
        entityManager.getTransaction().commit();
        return vacation;
    }

    @Override
    public Vacation update(Vacation vacation){
        entityManager.getTransaction().begin();
        entityManager.merge(vacation);
        entityManager.getTransaction().commit();
        return vacation;
    }

    @Override
    public void deleteAllByEmployee(Employee employee){
        entityManager.getTransaction().begin();
        entityManager
                .createQuery("select v from Vacation v where v.employee = :employee", Vacation.class)
                .setParameter(PARAMETER_EMP, employee)
                .getResultList().forEach(entityManager::remove);
        entityManager.getTransaction().commit();
    }
}
