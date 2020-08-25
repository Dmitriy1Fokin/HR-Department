package ru.fds.hrdepartment.common.converter.dto.impl;

import org.springframework.stereotype.Component;
import ru.fds.hrdepartment.common.converter.dto.ConverterDto;
import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.dto.DepartmentDto;

@Component
public class DepartmentDtoConverter implements ConverterDto<Department, DepartmentDto> {
    @Override
    public Department toEntity(DepartmentDto dto) {
        return new Department(dto.getId(), dto.getName());
    }

    @Override
    public DepartmentDto toDto(Department entity) {
        return new DepartmentDto(entity.getId(), entity.getName());
    }
}
