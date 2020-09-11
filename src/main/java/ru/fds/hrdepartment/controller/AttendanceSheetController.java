package ru.fds.hrdepartment.controller;

import io.swagger.api.AttendanceApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.hrdepartment.common.converter.dto.impl.AttendanceDtoConverter;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.AttendanceSheet;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.dto.AttendanceSheetDto;
import ru.fds.hrdepartment.service.AttendanceSheetService;
import ru.fds.hrdepartment.service.EmployeeService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AttendanceSheetController implements AttendanceApi{

    private final AttendanceSheetService attendanceSheetService;
    private final EmployeeService employeeService;
    private final AttendanceDtoConverter attendanceDtoConverter;

    public AttendanceSheetController(AttendanceSheetService attendanceSheetService,
                                     EmployeeService employeeService,
                                     AttendanceDtoConverter attendanceDtoConverter) {
        this.attendanceSheetService = attendanceSheetService;
        this.employeeService = employeeService;
        this.attendanceDtoConverter = attendanceDtoConverter;
    }

    @Override
    public ResponseEntity<AttendanceSheetDto> getAttendance(Long attendanceId) {
        return ResponseEntity.ok(attendanceDtoConverter.toDto(attendanceSheetService.getAttendance(attendanceId)
                .orElseThrow(() -> new NotFoundException("Attendance not found"))));
    }

    @Override
    public ResponseEntity<List<AttendanceSheetDto>> getAttendanceByEmployee(Long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee not found"));
        return ResponseEntity.ok(List.copyOf(attendanceDtoConverter
                .toDto(attendanceSheetService.getAttendanceByEmployee(employee))));
    }

    @Override
    public ResponseEntity<AttendanceSheetDto> insertAttendance(@Valid AttendanceSheetDto attendanceSheetDto) {
        AttendanceSheet attendanceSheet = attendanceDtoConverter.toEntity(attendanceSheetDto);
        return new ResponseEntity<>(attendanceDtoConverter
                .toDto(attendanceSheetService.insertAttendanceSheet(attendanceSheet)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AttendanceSheetDto> updateAttendance(@Valid AttendanceSheetDto attendanceSheetDto) {
        AttendanceSheet attendanceSheet = attendanceDtoConverter.toEntity(attendanceSheetDto);
        return new ResponseEntity<>(attendanceDtoConverter
                .toDto(attendanceSheetService.updateAttendanceSheet(attendanceSheet)), HttpStatus.ACCEPTED);
    }
}
