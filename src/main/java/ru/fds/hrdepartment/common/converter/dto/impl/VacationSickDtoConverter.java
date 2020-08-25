package ru.fds.hrdepartment.common.converter.dto.impl;

import org.springframework.stereotype.Component;
import ru.fds.hrdepartment.common.converter.dto.ConverterDto;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.VacationSick;
import ru.fds.hrdepartment.dto.VacationSickDto;
import ru.fds.hrdepartment.service.EmployeeService;

@Component
public class VacationSickDtoConverter implements ConverterDto<VacationSick, VacationSickDto> {

    private final EmployeeService employeeService;

    public VacationSickDtoConverter(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public VacationSick toEntity(VacationSickDto dto) {
        return new VacationSick(dto.getId(),
                employeeService.getEmployee(dto.getEmployeeId()).orElseThrow(() -> new NotFoundException("Employee not found")),
                dto.getDateStart(),
                dto.getDateEnd(),
                dto.getTypeOfVacationSick());
    }

    @Override
    public VacationSickDto toDto(VacationSick entity) {
        return new VacationSickDto(entity.getId(),
                entity.getEmployee().getId(),
                entity.getDateStart(),
                entity.getDateEnd(),
                entity.getTypeOfVacationSick());
    }
}
