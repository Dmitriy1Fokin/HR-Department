package ru.fds.hrdepartment.dao.impl;

import org.springframework.stereotype.Component;
import ru.fds.hrdepartment.dao.AttendanceSheetDao;
import ru.fds.hrdepartment.domain.AttendanceSheet;
import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.utils.HiberUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Component
public class AttendanceSheetDaoImpl implements AttendanceSheetDao {

    @Override
    public Optional<AttendanceSheet> findById(Long attendanceId){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        return Optional.ofNullable(entityManager.find(AttendanceSheet.class, attendanceId));
    }

    @Override
    public List<AttendanceSheet> findAllByEmployee(Employee employee){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        return entityManager
                .createQuery("select asheet from AttendanceSheet asheet where asheet.employee = :employee", AttendanceSheet.class)
                .setParameter("employee", employee)
                .getResultList();
    }

    @Override
    public Integer getWorkHoursInDepartment(Department department){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        Long hours = (Long) entityManager
                .createQuery("select sum(asheet.hourAtWork) from AttendanceSheet asheet where asheet.employee.department = :department")
                .setParameter("department", department)
                .getSingleResult();
        return hours.intValue();
    }

    @Override
    public AttendanceSheet save(AttendanceSheet attendanceSheet){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(attendanceSheet);
        entityManager.getTransaction().commit();
        return attendanceSheet;
    }

    @Override
    public AttendanceSheet update(AttendanceSheet attendanceSheet){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(attendanceSheet);
        entityManager.getTransaction().commit();
        return attendanceSheet;
    }

    @Override
    public void deleteAllByEmployee(Employee employee){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager
                .createQuery("select asheet from AttendanceSheet asheet where asheet.employee = :employee", AttendanceSheet.class)
                .setParameter("employee", employee)
                .getResultList().forEach(entityManager::remove);
        entityManager.getTransaction().commit();
    }
}
