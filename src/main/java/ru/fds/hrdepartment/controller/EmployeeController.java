package ru.fds.hrdepartment.controller;

import io.swagger.api.EmpApi;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/emp")
public class EmployeeController implements EmpApi {

    private final EmployeeService employeeService;
    private final EmployeeDtoConverter employeeDtoConverter;

    public EmployeeController(EmployeeService employeeService,
                              EmployeeDtoConverter employeeDtoConverter) {
        this.employeeService = employeeService;
        this.employeeDtoConverter = employeeDtoConverter;
    }

    @Override
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("employeeId") Long employeeId){
        return ResponseEntity.ok(employeeDtoConverter.toDto(employeeService
                .getEmployee(employeeId).orElseThrow(()-> new NotFoundException("Employee not found"))));
    }


    @Override
    @GetMapping("/all_days")
    public ResponseEntity<Integer> getWorkDaysByEmployee(@RequestParam("employeeId") Long employeeId){
        return ResponseEntity.ok(employeeService.getWorkDaysByEmployee(employeeId));
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteEmployee(@RequestParam("employeeId") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PostMapping("/new")
    public ResponseEntity<EmployeeDto> insertEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        Employee employee = employeeDtoConverter.toEntity(employeeDto);
        return ResponseEntity.ok(employeeDtoConverter.toDto(employeeService.insertEmployee(employee)));
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<EmployeeDto> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        Employee employee = employeeDtoConverter.toEntity(employeeDto);
        return ResponseEntity.ok(employeeDtoConverter.toDto(employeeService.updateEmployee(employee)));
    }

    @Override
    @GetMapping("/now/at_work")
    public ResponseEntity<List<EmployeeDto>> getEmployeesNowAtWork(){
        return ResponseEntity.ok(List.copyOf(employeeDtoConverter
                .toDto(employeeService.getEmployeeByTypeOfAttendance(LocalDate.now(), TypeOfAttendance.AT_WORK))));
    }

    @Override
    @GetMapping("/now/absence")
    public ResponseEntity<List<EmployeeDto>> getEmployeesNowInAbsence(){
        return ResponseEntity.ok(List.copyOf(employeeDtoConverter
                .toDto(employeeService.getEmployeeByTypeOfAttendance(LocalDate.now(), TypeOfAttendance.ABSENCE))));
    }

    @Override
    @GetMapping("/now/on_sick_leave")
    public ResponseEntity<List<EmployeeDto>> getEmployeesNowOnSickLeave(){
        return ResponseEntity.ok(List.copyOf(employeeDtoConverter
                .toDto(employeeService.getEmployeeByTypeOfAttendance(LocalDate.now(), TypeOfAttendance.ON_SICK_LEAVE))));
    }

    @Override
    @GetMapping("/now/on_vacation")
    public ResponseEntity<List<EmployeeDto>> getEmployeesNowOnVacation(){
        return ResponseEntity.ok(List.copyOf(employeeDtoConverter
                .toDto(employeeService.getEmployeeByTypeOfAttendance(LocalDate.now(), TypeOfAttendance.ON_VACATION))));
    }

    @Override
    @GetMapping("/date/at_work")
    public ResponseEntity<List<EmployeeDto>> getEmployeesDateAtWork(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
        return ResponseEntity.ok(List.copyOf(employeeDtoConverter
                .toDto(employeeService
                        .getEmployeeByTypeOfAttendance(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), TypeOfAttendance.AT_WORK))));
    }

    @Override
    @GetMapping("/date/absence")
    public ResponseEntity<List<EmployeeDto>> getEmployeesDateInAbsence(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
        return ResponseEntity.ok(List.copyOf(employeeDtoConverter
                .toDto(employeeService
                        .getEmployeeByTypeOfAttendance(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), TypeOfAttendance.ABSENCE))));
    }

    @Override
    @GetMapping("/date/on_sick_leave")
    public ResponseEntity<List<EmployeeDto>> getEmployeesDateOnSickLeave(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
        return ResponseEntity.ok(List.copyOf(employeeDtoConverter
                .toDto(employeeService
                        .getEmployeeByTypeOfAttendance(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), TypeOfAttendance.ON_SICK_LEAVE))));
    }

    @Override
    @GetMapping("/date/on_vacation")
    public ResponseEntity<List<EmployeeDto>> getEmployeesDateOnVacation(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
        return ResponseEntity.ok(List.copyOf(employeeDtoConverter
                .toDto(employeeService
                        .getEmployeeByTypeOfAttendance(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), TypeOfAttendance.ON_VACATION))));
    }
}
