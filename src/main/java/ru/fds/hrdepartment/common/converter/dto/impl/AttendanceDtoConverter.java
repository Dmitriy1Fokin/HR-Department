package ru.fds.hrdepartment.common.converter.dto.impl;

import org.springframework.stereotype.Component;
import ru.fds.hrdepartment.common.converter.dto.ConverterDto;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.AttendanceSheet;
import ru.fds.hrdepartment.dto.AttendanceSheetDto;
import ru.fds.hrdepartment.service.EmployeeService;

@Component
public class AttendanceDtoConverter implements ConverterDto<AttendanceSheet, AttendanceSheetDto> {

    private final EmployeeService employeeService;

    public AttendanceDtoConverter(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public AttendanceSheet toEntity(AttendanceSheetDto dto) {
        return new AttendanceSheet(dto.getId(),
                employeeService.getEmployee(dto.getEmployeeId())
                        .orElseThrow(() -> new NotFoundException("Employee not found")),
                dto.getWorkDate(),
                dto.getHourAtWork(),
                dto.getTypeOfAttendance());
    }

    @Override
    public AttendanceSheetDto toDto(AttendanceSheet entity) {
        return new AttendanceSheetDto(entity.getId(),
                entity.getEmployee().getId(),
                entity.getWorkDate(),
                entity.getHourAtWork(),
                entity.getTypeOfAttendance());
    }
}
