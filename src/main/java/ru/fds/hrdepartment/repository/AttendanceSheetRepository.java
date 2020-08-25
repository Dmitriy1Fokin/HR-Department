package ru.fds.hrdepartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fds.hrdepartment.domain.AttendanceSheet;
import ru.fds.hrdepartment.domain.Employee;

import java.util.Collection;

public interface AttendanceSheetRepository extends JpaRepository<AttendanceSheet, Long> {
    Collection<AttendanceSheet> findAllByEmployee(Employee employee);
}
