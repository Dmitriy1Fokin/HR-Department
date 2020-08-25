package ru.fds.hrdepartment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.hrdepartment.common.converter.dto.impl.AttendanceDtoConverter;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.AttendanceSheet;
import ru.fds.hrdepartment.dto.AttendanceSheetDto;
import ru.fds.hrdepartment.service.AttendanceSheetService;

import javax.validation.Valid;

@RestController
@RequestMapping("/attendance")
public class AttendanceSheetController {

    private final AttendanceSheetService attendanceSheetService;
    private final AttendanceDtoConverter attendanceDtoConverter;

    public AttendanceSheetController(AttendanceSheetService attendanceSheetService,
                                     AttendanceDtoConverter attendanceDtoConverter) {
        this.attendanceSheetService = attendanceSheetService;
        this.attendanceDtoConverter = attendanceDtoConverter;
    }

    @GetMapping("/{attendanceId}")
    public AttendanceSheetDto getAttendance(@PathVariable("attendanceId") Long attendanceId){
        return attendanceDtoConverter.toDto(attendanceSheetService.getAttendance(attendanceId)
                .orElseThrow(() -> new NotFoundException("Attendance not found")));
    }

    @PostMapping("/new")
    public AttendanceSheetDto insertAttendance(@Valid @RequestBody AttendanceSheetDto attendanceSheetDto){
        AttendanceSheet attendanceSheet = attendanceDtoConverter.toEntity(attendanceSheetDto);
        return attendanceDtoConverter.toDto(attendanceSheetService.setAttendanceSheet(attendanceSheet));
    }

    @PutMapping("/update")
    public AttendanceSheetDto updateAttendance(@Valid @RequestBody AttendanceSheetDto attendanceSheetDto){
        AttendanceSheet attendanceSheet = attendanceDtoConverter.toEntity(attendanceSheetDto);
        return attendanceDtoConverter.toDto(attendanceSheetService.updateAttendanceSheet(attendanceSheet));
    }
}
