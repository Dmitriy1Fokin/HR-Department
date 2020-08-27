package ru.fds.hrdepartment.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.fds.hrdepartment.TestUtils;
import ru.fds.hrdepartment.domain.AttendanceSheet;
import ru.fds.hrdepartment.repository.AttendanceSheetRepository;
import ru.fds.hrdepartment.service.AttendanceSheetService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AttendanceSheetServiceImplTest {

    @Autowired
    private AttendanceSheetService attendanceSheetService;

    @MockBean
    private AttendanceSheetRepository attendanceSheetRepository;

    @BeforeEach
    public void setUp(){
        AttendanceSheet attendanceSheet = TestUtils.getAttendanceSheet();

        Optional<AttendanceSheet> attendanceSheetOptional = Optional.of(attendanceSheet);
        Mockito.when(attendanceSheetRepository.findById(Mockito.anyLong()))
                .thenReturn(attendanceSheetOptional);
        Mockito.when(attendanceSheetRepository.save(Mockito.any(AttendanceSheet.class)))
                .thenReturn(attendanceSheet);
    }

    @Test
    void getAttendance() {
        Optional<AttendanceSheet> attendanceSheet = attendanceSheetService
                .getAttendance(TestUtils.getAttendanceSheet().getId());

        assertEquals(TestUtils.getAttendanceSheet().getId(), attendanceSheet.get().getId());
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