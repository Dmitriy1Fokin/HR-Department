package ru.fds.hrdepartment.dao;

import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.Position;
import ru.fds.hrdepartment.domain.helpertype.TypeOfAttendance;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
    Optional<Employee> findById(Long employeeId);
    Integer getWorkDaysByEmployee(Long employeeId);
    List<Employee> getEmployeeByTypeOfAttendance(LocalDate date, TypeOfAttendance typeOfAttendance);
    List<Employee> findAllByDepartmentAndPosition(Department department, Position position);
    Integer countOfSickEmployees(Collection<Employee> employees, LocalDate dateEnd);
    Integer countOfEmployeesInVacation(Collection<Employee> employees, LocalDate date);
    Employee save(Employee employee);
    Employee update(Employee employee);
    void delete(Long employeeId);
}
