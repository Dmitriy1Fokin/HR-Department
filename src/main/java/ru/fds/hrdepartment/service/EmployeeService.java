package ru.fds.hrdepartment.service;

import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.helpertype.TypeOfAttendance;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> getEmployee(Long employeeId);
    Integer getWorkDaysByEmployee(Long employeeId);
    void deleteEmployee(Long employeeId);
    Employee setEmployee(Employee employee);
    Collection<Employee> getEmployeeByTypeOfAttendance(LocalDate date, TypeOfAttendance typeOfAttendance);
}
