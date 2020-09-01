package ru.fds.hrdepartment.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.fds.hrdepartment.TestUtils;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.dao.AttendanceSheetDao;
import ru.fds.hrdepartment.domain.AttendanceSheet;
import ru.fds.hrdepartment.service.AttendanceSheetService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AttendanceSheetServiceImplTest {

    @Autowired
    private AttendanceSheetService attendanceSheetService;

    @MockBean
    private AttendanceSheetDao attendanceSheetDao;

    @BeforeEach
    public void setUp(){
        AttendanceSheet attendanceSheet = TestUtils.getAttendanceSheet();

        Optional<AttendanceSheet> attendanceSheetOptional = Optional.of(attendanceSheet);
        Mockito.when(attendanceSheetDao.findById(Mockito.anyLong()))
                .thenReturn(attendanceSheetOptional);
        Mockito.when(attendanceSheetDao.save(Mockito.any(AttendanceSheet.class)))
                .thenReturn(attendanceSheet);
        Mockito.when(attendanceSheetDao.update(Mockito.any(AttendanceSheet.class)))
                .thenReturn(attendanceSheet);
    }

    @Test
    void getAttendance() {
        Optional<AttendanceSheet> attendanceSheet = attendanceSheetService
                .getAttendance(TestUtils.getAttendanceSheet().getId());

        attendanceSheet.ifPresentOrElse(attendanceSheet1 -> assertEquals(TestUtils.getAttendanceSheet().getId(), attendanceSheet1.getId()),
                () -> {throw new NotFoundException("AttendanceSheet not found");});
    }

    @Test
    void insertAttendanceSheet() {
        AttendanceSheet attendanceSheet = attendanceSheetService.insertAttendanceSheet(TestUtils.getAttendanceSheet());

        assertEquals(TestUtils.getAttendanceSheet().getId(), attendanceSheet.getId());
    }

    @Test
    void updateAttendanceSheet() {
        AttendanceSheet attendanceSheet = attendanceSheetService.updateAttendanceSheet(TestUtils.getAttendanceSheet());

        assertEquals(TestUtils.getAttendanceSheet().getId(), attendanceSheet.getId());
    }
}