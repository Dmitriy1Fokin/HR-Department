package ru.fds.hrdepartment.controller;

import io.swagger.api.DepApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.hrdepartment.common.converter.dto.impl.DepartmentDtoConverter;
import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.dto.DepartmentDto;
import ru.fds.hrdepartment.service.DepartmentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/dep")
public class DepartmentController implements DepApi {

    private final DepartmentService departmentService;
    private final DepartmentDtoConverter departmentDtoConverter;

    public DepartmentController(DepartmentService departmentService,
                                DepartmentDtoConverter departmentDtoConverter) {
        this.departmentService = departmentService;
        this.departmentDtoConverter = departmentDtoConverter;
    }

    @Override
    @GetMapping("/total_hour")
    public ResponseEntity<Integer> getWorkHoursInDepartment(@RequestParam("departmentId") Long departmentId){
        return ResponseEntity.ok(departmentService.getWorkHoursInDepartment(departmentId));
    }

    @Override
    @PostMapping("/new")
    public ResponseEntity<DepartmentDto> insertDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        Department department = departmentDtoConverter.toEntity(departmentDto);
        return ResponseEntity.ok(departmentDtoConverter.toDto(departmentService.insertDepartment(department)));
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<DepartmentDto> updateDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        Department department = departmentDtoConverter.toEntity(departmentDto);
        return ResponseEntity.ok(departmentDtoConverter.toDto(departmentService.updateDepartment(department)));
    }

}
