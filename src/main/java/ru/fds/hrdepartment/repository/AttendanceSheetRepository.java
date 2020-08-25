package ru.fds.hrdepartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.fds.hrdepartment.domain.AttendanceSheet;
import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.domain.Employee;

import java.util.Collection;

public interface AttendanceSheetRepository extends JpaRepository<AttendanceSheet, Long> {
    Collection<AttendanceSheet> findAllByEmployee(Employee employee);
    @Query("select sum(asheet.hourAtWork) from AttendanceSheet asheet where asheet.employee.department = :department")
    Integer getWorkHoursInDepartment(Department department);
}
