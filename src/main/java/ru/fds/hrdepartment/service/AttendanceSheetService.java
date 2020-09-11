package ru.fds.hrdepartment.service;

import ru.fds.hrdepartment.domain.AttendanceSheet;
import ru.fds.hrdepartment.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface AttendanceSheetService {
    Optional<AttendanceSheet> getAttendance(Long attendanceId);
    List<AttendanceSheet> getAttendanceByEmployee(Employee employee);
    AttendanceSheet insertAttendanceSheet(AttendanceSheet attendanceSheet);
    AttendanceSheet updateAttendanceSheet(AttendanceSheet attendanceSheet);
}
