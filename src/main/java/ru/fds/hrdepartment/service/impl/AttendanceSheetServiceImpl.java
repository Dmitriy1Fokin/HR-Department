package ru.fds.hrdepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fds.hrdepartment.dao.AttendanceSheetDao;
import ru.fds.hrdepartment.domain.AttendanceSheet;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.service.AttendanceSheetService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AttendanceSheetServiceImpl implements AttendanceSheetService {

    private final AttendanceSheetDao attendanceSheetDao;

    public AttendanceSheetServiceImpl(AttendanceSheetDao attendanceSheetDao) {
        this.attendanceSheetDao = attendanceSheetDao;
    }

    @Override
    public Optional<AttendanceSheet> getAttendance(Long attendanceId) {
        log.debug("getAttendance. attendanceId: {}", attendanceId);
        return attendanceSheetDao.findById(attendanceId);
    }

    @Override
    public List<AttendanceSheet> getAttendanceByEmployee(Employee employee) {
        log.debug("getAttendanceByEmployee. employee: {}", employee);
        return attendanceSheetDao.findAllByEmployee(employee);
    }

    @Override
    @Transactional
    public AttendanceSheet insertAttendanceSheet(AttendanceSheet attendanceSheet) {
        attendanceSheet = attendanceSheetDao.save(attendanceSheet);
        log.info("insert attendanceSheet = {}", attendanceSheet);
        return attendanceSheet;
    }

    @Override
    @Transactional
    public AttendanceSheet updateAttendanceSheet(AttendanceSheet attendanceSheet) {
        attendanceSheet = attendanceSheetDao.update(attendanceSheet);
        log.info("update attendanceSheet = {}", attendanceSheet);
        return attendanceSheet;
    }
}
