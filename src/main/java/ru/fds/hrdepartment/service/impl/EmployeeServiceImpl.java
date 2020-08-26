package ru.fds.hrdepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.helpertype.TypeOfAttendance;
import ru.fds.hrdepartment.repository.AttendanceSheetRepository;
import ru.fds.hrdepartment.repository.EmployeeRepository;
import ru.fds.hrdepartment.repository.SickLeaveRepository;
import ru.fds.hrdepartment.repository.VacationRepository;
import ru.fds.hrdepartment.service.EmployeeService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceSheetRepository attendanceSheetRepository;
    private final VacationRepository vacationRepository;
    private final SickLeaveRepository sickLeaveRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               AttendanceSheetRepository attendanceSheetRepository,
                               VacationRepository vacationRepository,
                               SickLeaveRepository sickLeaveRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceSheetRepository = attendanceSheetRepository;
        this.vacationRepository = vacationRepository;
        this.sickLeaveRepository = sickLeaveRepository;
    }

    @Override
    public Optional<Employee> getEmployee(Long employeeId) {
        log.debug("getEmployee. EmployeeId = {}", employeeId);
        return employeeRepository.findById(employeeId);
    }

    @Override
    public Integer getWorkDaysByEmployee(Long employeeId) {
        log.debug("getWorkDaysByEmployee. employeeId = {}", employeeId);
        return employeeRepository.getWorkDaysByEmployee(employeeId);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long employeeId) {
        getEmployee(employeeId).ifPresent(employee -> {
            vacationRepository.deleteAll(vacationRepository.findAllByEmployee(employee));
            sickLeaveRepository.deleteAll(sickLeaveRepository.findAllByEmployee(employee));
            attendanceSheetRepository.deleteAll(attendanceSheetRepository.findAllByEmployee(employee));
            employeeRepository.delete(employee);
        });

        log.info("deleted employee with id = {}", employeeId);
    }

    @Override
    @Transactional
    public Employee insertEmployee(Employee employee) {
        employee = employeeRepository.save(employee);
        log.info("insert new employee: {}", employee);
        return employee;
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) {
        employee = employeeRepository.save(employee);
        log.info("update employee: {}", employee);
        return employee;
    }

    @Override
    public Collection<Employee> getEmployeeByTypeOfAttendance(LocalDate date, TypeOfAttendance typeOfAttendance) {
        log.debug("getEmployeeByTypeOfAttendance. date: {}, typeOfAttendance:{}", date, typeOfAttendance);
        Collection<Employee> employees = employeeRepository.getEmployeeByTypeOfAttendance(date, typeOfAttendance);
        log.debug("getEmployeeByTypeOfAttendance. employees: {}", employees);
        return employees;
    }
}
