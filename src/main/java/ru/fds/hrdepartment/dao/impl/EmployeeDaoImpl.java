package ru.fds.hrdepartment.dao.impl;

import org.springframework.stereotype.Component;
import ru.fds.hrdepartment.dao.EmployeeDao;
import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.Position;
import ru.fds.hrdepartment.domain.helpertype.TypeOfAttendance;
import ru.fds.hrdepartment.utils.HiberUtils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public Optional<Employee> findById(Long employeeId){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        return Optional.ofNullable(entityManager.find(Employee.class, employeeId));
    }

    @Override
    public Integer getWorkDaysByEmployee(Long employeeId){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        Long days = (Long) entityManager
                .createQuery("select count(asheet) " +
                        "from AttendanceSheet asheet " +
                        "where asheet.employee.id = :employeeId")
                .setParameter("employeeId", employeeId)
                .getSingleResult();
        return days.intValue();
    }

    @Override
    public List<Employee> getEmployeeByTypeOfAttendance(LocalDate date, TypeOfAttendance typeOfAttendance){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        return entityManager
                .createQuery("select asheet.employee " +
                        "from AttendanceSheet asheet " +
                        "where asheet.typeOfAttendance = :typeOfAttendance and asheet.workDate = :date", Employee.class)
                .setParameter("typeOfAttendance", typeOfAttendance)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Employee> findAllByDepartmentAndPosition(Department department, Position position){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        return entityManager
                .createQuery("select emp " +
                        "from Employee emp " +
                        "where emp.department = :department and emp.position = :position", Employee.class)
                .setParameter("department", department)
                .setParameter("position", position)
                .getResultList();
    }

    @Override
    public Integer countOfSickEmployees(Collection<Employee> employees, LocalDate dateEnd){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        Long days = (Long) entityManager
                .createQuery("select count(sl) " +
                        "from SickLeave sl " +
                        "where sl.employee in :employees and (sl.dateEnd is null or sl.dateEnd >= :dateEnd)")
                .setParameter("employees", employees)
                .setParameter("dateEnd", dateEnd)
                .getSingleResult();
        return days.intValue();
    }

    @Override
    public Integer countOfEmployeesInVacation(Collection<Employee> employees, LocalDate date){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        Long days = (Long) entityManager
                .createQuery("select count(v) " +
                        "from Vacation v " +
                        "where v.employee in :employees and v.dateStart <= :date " +
                        "and (v.dateEnd >= :date or v.dateEnd is null )")
                .setParameter("employees", employees)
                .setParameter("date", date)
                .getSingleResult();
        return days.intValue();
    }

    @Override
    public Employee save(Employee employee){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        return employee;
    }

    @Override
    public Employee update(Employee employee){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(employee);
        entityManager.getTransaction().commit();
        return employee;
    }

    @Override
    public void delete(Long employeeId){
        EntityManager entityManager = HiberUtils.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Employee employee = entityManager.find(Employee.class, employeeId);
        if(Objects.nonNull(employee)) {
            entityManager.remove(employee);
        }

        entityManager.getTransaction().commit();
    }


}
