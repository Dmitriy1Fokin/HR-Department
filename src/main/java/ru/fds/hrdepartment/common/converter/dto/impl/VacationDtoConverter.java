package ru.fds.hrdepartment.common.converter.dto.impl;

import org.springframework.stereotype.Component;
import ru.fds.hrdepartment.common.converter.dto.ConverterDto;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.Vacation;
import ru.fds.hrdepartment.dto.VacationDto;
import ru.fds.hrdepartment.service.EmployeeService;

@Component
public class VacationDtoConverter implements ConverterDto<Vacation, VacationDto> {

    private final EmployeeService employeeService;

    public VacationDtoConverter(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Vacation toEntity(VacationDto dto) {
        return new Vacation(dto.getId(),
                employeeService.getEmployee(dto.getEmployeeId()).orElseThrow(() -> new NotFoundException("Employee not found")),
                dto.getDateStart(),
                dto.getDateEnd(),
                dto.getTypeOfVacation());
    }

    @Override
    public VacationDto toDto(Vacation entity) {
        return new VacationDto(entity.getId(),
                entity.getEmployee().getId(),
                entity.getDateStart(),
                entity.getDateEnd(),
                entity.getTypeOfVacation());
    }
}
