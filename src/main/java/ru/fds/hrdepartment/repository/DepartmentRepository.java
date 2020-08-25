package ru.fds.hrdepartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fds.hrdepartment.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
