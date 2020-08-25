package ru.fds.hrdepartment.common.converter.dto.impl;

import org.springframework.stereotype.Component;
import ru.fds.hrdepartment.common.converter.dto.ConverterDto;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.dto.EmployeeDto;
import ru.fds.hrdepartment.service.DepartmentService;
import ru.fds.hrdepartment.service.PositionService;

@Component
public class EmployeeDtoConverter implements ConverterDto<Employee, EmployeeDto> {

    private final PositionService positionService;
    private final DepartmentService departmentService;

    public EmployeeDtoConverter(PositionService positionService,
                                DepartmentService departmentService) {
        this.positionService = positionService;
        this.departmentService = departmentService;
    }

    @Override
    public Employee toEntity(EmployeeDto dto) {
        return new Employee(dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getDateIn(),
                dto.getDateOut(),
                departmentService.getDepartment(dto.getDepartmentId()).orElseThrow(() -> new NotFoundException("Department not found")),
                positionService.getPosition(dto.getPositionId()).orElseThrow(() -> new NotFoundException("Position not found")));
    }

    @Override
    public EmployeeDto toDto(Employee entity) {
        return new EmployeeDto(entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDateIn(),
                entity.getDateOut(),
                entity.getDepartment().getId(),
                entity.getPosition().getId());
    }
}
