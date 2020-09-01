package ru.fds.hrdepartment.dao;

import ru.fds.hrdepartment.domain.Department;

import java.util.Optional;

public interface DepartmentDao {
    Optional<Department> findById(Long departmentId);
    Department save(Department department);
    Department update(Department department);
}
