package ru.fds.hrdepartment.service;

import ru.fds.hrdepartment.domain.AttendanceSheet;
import ru.fds.hrdepartment.domain.helpertype.TypeOfAttendance;

public interface AttendanceSheetService {
    AttendanceSheet setAttendanceSheet(AttendanceSheet attendanceSheet);
    AttendanceSheet updateAttendanceSheet(AttendanceSheet attendanceSheet);
    Integer getCountEmployeeByTypeOfAttendance(TypeOfAttendance typeOfAttendance);
}
