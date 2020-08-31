package ru.fds.hrdepartment.controller;

import io.swagger.api.DepApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.hrdepartment.common.converter.dto.impl.DepartmentDtoConverter;
import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.dto.DepartmentDto;
import ru.fds.hrdepartment.service.DepartmentService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class DepartmentController implements DepApi {

    private final DepartmentService departmentService;
    private final DepartmentDtoConverter departmentDtoConverter;

    public DepartmentController(DepartmentService departmentService,
                                DepartmentDtoConverter departmentDtoConverter) {
        this.departmentService = departmentService;
        this.departmentDtoConverter = departmentDtoConverter;
    }

    @Override
    public ResponseEntity<Integer> getWorkHoursInDepartment(@NotNull @Valid Long departmentId){
        return ResponseEntity.ok(departmentService.getWorkHoursInDepartment(departmentId));
    }

    @Override
    public ResponseEntity<DepartmentDto> insertDepartment(@Valid DepartmentDto departmentDto){
        Department department = departmentDtoConverter.toEntity(departmentDto);
        return new ResponseEntity<>(departmentDtoConverter.toDto(departmentService.insertDepartment(department)),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DepartmentDto> updateDepartment(@Valid DepartmentDto departmentDto){
        Department department = departmentDtoConverter.toEntity(departmentDto);
        return new ResponseEntity<>(departmentDtoConverter.toDto(departmentService.updateDepartment(department)),
                HttpStatus.ACCEPTED);
    }

}
