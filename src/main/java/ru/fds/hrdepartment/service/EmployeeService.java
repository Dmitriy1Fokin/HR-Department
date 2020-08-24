package ru.fds.hrdepartment.service;

import ru.fds.hrdepartment.domain.Employee;

import java.time.LocalDate;
import java.util.Optional;

public interface EmployeeService {
    Integer getWorkDaysByEmployee(Long employeeId);
    Optional<LocalDate> getLastVacationByEmployee(Long employeeId);
    Employee deleteEmployee(Long employeeId);
    Employee setEmployee(Employee employee);
}
