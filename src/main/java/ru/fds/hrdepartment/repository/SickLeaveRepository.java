package ru.fds.hrdepartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.SickLeave;

import java.util.Collection;

public interface SickLeaveRepository extends JpaRepository<SickLeave, Long> {
    Collection<SickLeave> findAllByEmployee(Employee employee);
}
