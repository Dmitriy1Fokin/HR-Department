package ru.fds.hrdepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fds.hrdepartment.domain.AttendanceSheet;
import ru.fds.hrdepartment.repository.AttendanceSheetRepository;
import ru.fds.hrdepartment.service.AttendanceSheetService;

import java.util.Optional;

@Slf4j
@Service
public class AttendanceSheetServiceImpl implements AttendanceSheetService {

    private final AttendanceSheetRepository attendanceSheetRepository;

    public AttendanceSheetServiceImpl(AttendanceSheetRepository attendanceSheetRepository) {
        this.attendanceSheetRepository = attendanceSheetRepository;
    }

    @Override
    public Optional<AttendanceSheet> getAttendance(Long attendanceId) {
        log.debug("getAttendance. attendanceId: {}", attendanceId);
        return attendanceSheetRepository.findById(attendanceId);
    }

    @Override
    @Transactional
    public AttendanceSheet setAttendanceSheet(AttendanceSheet attendanceSheet) {
        attendanceSheet = attendanceSheetRepository.save(attendanceSheet);
        log.info("insert attendanceSheet = {}", attendanceSheet);
        return attendanceSheet;
    }

    @Override
    @Transactional
    public AttendanceSheet updateAttendanceSheet(AttendanceSheet attendanceSheet) {
        attendanceSheet = attendanceSheetRepository.save(attendanceSheet);
        log.info("update attendanceSheet = {}", attendanceSheet);
        return attendanceSheet;
    }
}
