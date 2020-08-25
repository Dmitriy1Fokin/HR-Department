package ru.fds.hrdepartment.service;

import ru.fds.hrdepartment.domain.AttendanceSheet;

import java.util.Optional;

public interface AttendanceSheetService {
    Optional<AttendanceSheet> getAttendance(Long attendanceId);
    AttendanceSheet setAttendanceSheet(AttendanceSheet attendanceSheet);
    AttendanceSheet updateAttendanceSheet(AttendanceSheet attendanceSheet);
}
