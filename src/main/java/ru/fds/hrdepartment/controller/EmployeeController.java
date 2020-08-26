package ru.fds.hrdepartment.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.hrdepartment.common.converter.dto.impl.EmployeeDtoConverter;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.helpertype.TypeOfAttendance;
import ru.fds.hrdepartment.dto.EmployeeDto;
import ru.fds.hrdepartment.service.EmployeeService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;


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
        return employeeDtoConverter.toDto(employeeService.insertEmployee(employee));
    }

    @PutMapping("/update")
    public EmployeeDto updateEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        Employee employee = employeeDtoConverter.toEntity(employeeDto);
        return employeeDtoConverter.toDto(employeeService.updateEmployee(employee));
    }

    @GetMapping("/now/at_work")
    public Collection<EmployeeDto> getEmployeesNowAtWork(){
        return employeeDtoConverter.toDto(employeeService.getEmployeeByTypeOfAttendance(LocalDate.now(), TypeOfAttendance.AT_WORK));
    }

    @GetMapping("/now/absence")
    public Collection<EmployeeDto> getEmployeesNowInAbsence(){
        return employeeDtoConverter.toDto(employeeService.getEmployeeByTypeOfAttendance(LocalDate.now(), TypeOfAttendance.ABSENCE));
    }

    @GetMapping("/now/on_sick_leave")
    public Collection<EmployeeDto> getEmployeesNowOnSickLeave(){
        return employeeDtoConverter.toDto(employeeService.getEmployeeByTypeOfAttendance(LocalDate.now(), TypeOfAttendance.ON_SICK_LEAVE));
    }

    @GetMapping("/now/on_vacation")
    public Collection<EmployeeDto> getEmployeesNowOnVacation(){
        return employeeDtoConverter.toDto(employeeService.getEmployeeByTypeOfAttendance(LocalDate.now(), TypeOfAttendance.ON_VACATION));
    }

    @GetMapping("/date/at_work")
    public Collection<EmployeeDto> getEmployeesDateAtWork(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return employeeDtoConverter.toDto(employeeService.getEmployeeByTypeOfAttendance(date, TypeOfAttendance.AT_WORK));
    }

    @GetMapping("/date/absence")
    public Collection<EmployeeDto> getEmployeesDateInAbsence(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return employeeDtoConverter.toDto(employeeService.getEmployeeByTypeOfAttendance(date, TypeOfAttendance.ABSENCE));
    }

    @GetMapping("/date/on_sick_leave")
    public Collection<EmployeeDto> getEmployeesDateOnSickLeave(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return employeeDtoConverter.toDto(employeeService.getEmployeeByTypeOfAttendance(date, TypeOfAttendance.ON_SICK_LEAVE));
    }

    @GetMapping("/date/on_vacation")
    public Collection<EmployeeDto> getEmployeesDateOnVacation(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return employeeDtoConverter.toDto(employeeService.getEmployeeByTypeOfAttendance(date, TypeOfAttendance.ON_VACATION));
    }
}
