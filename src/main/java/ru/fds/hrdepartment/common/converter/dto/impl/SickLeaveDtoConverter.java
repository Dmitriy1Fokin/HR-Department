package ru.fds.hrdepartment.common.converter.dto.impl;

import org.springframework.stereotype.Component;
import ru.fds.hrdepartment.common.converter.dto.ConverterDto;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.SickLeave;
import ru.fds.hrdepartment.dto.SickLeaveDto;
import ru.fds.hrdepartment.service.EmployeeService;

@Component
public class SickLeaveDtoConverter implements ConverterDto<SickLeave, SickLeaveDto> {

    private final EmployeeService employeeService;

    public SickLeaveDtoConverter(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public SickLeave toEntity(SickLeaveDto dto) {
        return new SickLeave(dto.getId(),
                employeeService.getEmployee(dto.getEmployeeId()).orElseThrow(() -> new NotFoundException("Employee not found")),
                dto.getDateStart(),
                dto.getDateEnd(),
                dto.getTypeOfSickLeave());
    }

    @Override
    public SickLeaveDto toDto(SickLeave entity) {
        return new SickLeaveDto(entity.getId(),
                entity.getEmployee().getId(),
                entity.getDateStart(),
                entity.getDateEnd(),
                entity.getTypeOfSickLeave());
    }
}
