package ru.fds.hrdepartment.dao;

import ru.fds.hrdepartment.domain.AttendanceSheet;
import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface AttendanceSheetDao {
    Optional<AttendanceSheet> findById(Long attendanceId);
    List<AttendanceSheet> findAllByEmployee(Employee employee);
    Integer getWorkHoursInDepartment(Department department);
    AttendanceSheet save(AttendanceSheet attendanceSheet);
    AttendanceSheet update(AttendanceSheet attendanceSheet);
    void deleteAllByEmployee(Employee employee);
}
