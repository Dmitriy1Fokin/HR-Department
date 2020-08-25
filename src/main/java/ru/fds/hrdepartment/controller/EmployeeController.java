package ru.fds.hrdepartment.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.hrdepartment.common.converter.dto.impl.EmployeeDtoConverter;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.dto.EmployeeDto;
import ru.fds.hrdepartment.service.EmployeeService;

import javax.validation.Valid;


@RestController
@RequestMapping("/emp")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeDtoConverter employeeDtoConverter;

    public EmployeeController(EmployeeService employeeService,
                              EmployeeDtoConverter employeeDtoConverter) {
        this.employeeService = employeeService;
        this.employeeDtoConverter = employeeDtoConverter;
    }

    @GetMapping("/{employeeId}")
    public EmployeeDto getEmployee(@PathVariable("employeeId") Long employeeId){
        return employeeDtoConverter.toDto(employeeService
                .getEmployee(employeeId).orElseThrow(()-> new NotFoundException("Employee not found")));
    }


    @GetMapping("/all_days")
    public Integer getWorkDaysByEmployee(@RequestParam("employeeId") Long employeeId){
        return employeeService.getWorkDaysByEmployee(employeeId);
    }

    @DeleteMapping("/delete")
    public void deleteEmployee(@RequestParam("employeeId") Long employeeId){
        employeeService.deleteEmployee(employeeId);
    }

    @PostMapping("/new")
    public EmployeeDto insertEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        Employee employee = employeeDtoConverter.toEntity(employeeDto);
        return employeeDtoConverter.toDto(employeeService.setEmployee(employee));
    }

}
