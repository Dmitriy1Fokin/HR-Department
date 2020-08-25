package ru.fds.hrdepartment.service;

import ru.fds.hrdepartment.domain.Department;

import java.util.Optional;

public interface DepartmentService {
    Optional<Department> getDepartment(Long departmentId);
    Integer getWorkHoursInDepartment(Long departmentId);
}
