package ru.fds.hrdepartment.controller;

import io.swagger.api.EmpApi;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.hrdepartment.common.converter.dto.impl.EmployeeDtoConverter;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.helpertype.TypeOfAttendance;
import ru.fds.hrdepartment.dto.EmployeeDto;
import ru.fds.hrdepartment.service.EmployeeService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


@RestController
public class EmployeeController implements EmpApi {

    private final EmployeeService employeeService;
    private final EmployeeDtoConverter employeeDtoConverter;

    public EmployeeController(EmployeeService employeeService,
                              EmployeeDtoConverter employeeDtoConverter) {
        this.employeeService = employeeService;
        this.employeeDtoConverter = employeeDtoConverter;
    }

    @Override
    public ResponseEntity<EmployeeDto> getEmployee(Long employeeId){
        return ResponseEntity.ok(employeeDtoConverter.toDto(employeeService
                .getEmployee(employeeId).orElseThrow(()-> new NotFoundException("Employee not found"))));
    }

    @Override
    public ResponseEntity<Integer> getWorkDaysByEmployee(@NotNull @Valid Long employeeId){
        return ResponseEntity.ok(employeeService.getWorkDaysByEmployee(employeeId));
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(@NotNull @Valid Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmployeeDto> insertEmployee(@Valid EmployeeDto employeeDto){
        Employee employee = employeeDtoConverter.toEntity(employeeDto);
        return new ResponseEntity<>(employeeDtoConverter.toDto(employeeService.insertEmployee(employee)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<EmployeeDto> updateEmployee(@Valid EmployeeDto employeeDto){
        Employee employee = employeeDtoConverter.toEntity(employeeDto);
        return new ResponseEntity<>(employeeDtoConverter.toDto(employeeService.updateEmployee(employee)), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<EmployeeDto>> getEmployeesNowAtWork(){
        return ResponseEntity.ok(List.copyOf(employeeDtoConverter
                .toDto(employeeService.getEmployeeByTypeOfAttendance(LocalDate.now(), TypeOfAttendance.AT_WORK))));
    }

    @Override
    public ResponseEntity<List<EmployeeDto>> getEmployeesNowInAbsence(){
        return ResponseEntity.ok(List.copyOf(employeeDtoConverter
                .toDto(employeeService.getEmployeeByTypeOfAttendance(LocalDate.now(), TypeOfAttendance.ABSENCE))));
    }

    @Override
    public ResponseEntity<List<EmployeeDto>> getEmployeesNowOnSickLeave(){
        return ResponseEntity.ok(List.copyOf(employeeDtoConverter
                .toDto(employeeService.getEmployeeByTypeOfAttendance(LocalDate.now(), TypeOfAttendance.ON_SICK_LEAVE))));
    }

    @Override
    public ResponseEntity<List<EmployeeDto>> getEmployeesNowOnVacation(){
        return ResponseEntity.ok(List.copyOf(employeeDtoConverter
                .toDto(employeeService.getEmployeeByTypeOfAttendance(LocalDate.now(), TypeOfAttendance.ON_VACATION))));
    }

    @Override
    public ResponseEntity<List<EmployeeDto>> getEmployeesDateAtWork(@NotNull @Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
        return ResponseEntity.ok(List.copyOf(employeeDtoConverter
                .toDto(employeeService
                        .getEmployeeByTypeOfAttendance(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), TypeOfAttendance.AT_WORK))));
    }

    @Override
    public ResponseEntity<List<EmployeeDto>> getEmployeesDateInAbsence(@NotNull @Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
        return ResponseEntity.ok(List.copyOf(employeeDtoConverter
                .toDto(employeeService
                        .getEmployeeByTypeOfAttendance(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), TypeOfAttendance.ABSENCE))));
    }

    @Override
    public ResponseEntity<List<EmployeeDto>> getEmployeesDateOnSickLeave(@NotNull @Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
        return ResponseEntity.ok(List.copyOf(employeeDtoConverter
                .toDto(employeeService
                        .getEmployeeByTypeOfAttendance(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), TypeOfAttendance.ON_SICK_LEAVE))));
    }

    @Override
    public ResponseEntity<List<EmployeeDto>> getEmployeesDateOnVacation(@NotNull @Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
        return ResponseEntity.ok(List.copyOf(employeeDtoConverter
                .toDto(employeeService
                        .getEmployeeByTypeOfAttendance(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), TypeOfAttendance.ON_VACATION))));
    }
}
