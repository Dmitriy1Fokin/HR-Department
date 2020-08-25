package ru.fds.hrdepartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.fds.hrdepartment.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select count(asheet) from AttendanceSheet asheet where asheet.employee.id = :employeeId")
    Integer getWorkDaysByEmployee(@Param("employeeId") Long employeeId);
}
