package ru.fds.hrdepartment.controller;

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
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DepartmentDtoConverter departmentDtoConverter;

    public DepartmentController(DepartmentService departmentService,
                                DepartmentDtoConverter departmentDtoConverter) {
        this.departmentService = departmentService;
        this.departmentDtoConverter = departmentDtoConverter;
    }

    @GetMapping("/total_hour")
    public Integer getWorkHoursInDepartment(@RequestParam("departmentId") Long departmentId){
        return departmentService.getWorkHoursInDepartment(departmentId);
    }

    @PostMapping("/new")
    public DepartmentDto insertDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        Department department = departmentDtoConverter.toEntity(departmentDto);
        return departmentDtoConverter.toDto(departmentService.insertDepartment(department));
    }

    @PutMapping("/update")
    public DepartmentDto updateDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        Department department = departmentDtoConverter.toEntity(departmentDto);
        return departmentDtoConverter.toDto(departmentService.updateDepartment(department));
    }

}
