package ru.fds.hrdepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fds.hrdepartment.domain.AttendanceSheet;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.Vacation;
import ru.fds.hrdepartment.domain.helpertype.TypeOfAttendance;
import ru.fds.hrdepartment.repository.AttendanceSheetRepository;
import ru.fds.hrdepartment.repository.EmployeeRepository;
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

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               AttendanceSheetRepository attendanceSheetRepository,
                               VacationRepository vacationRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceSheetRepository = attendanceSheetRepository;
        this.vacationRepository = vacationRepository;
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
        Optional<Employee> employee = getEmployee(employeeId);
        if(employee.isEmpty()){
            return;
        }

        Collection<Vacation> vacations = vacationRepository.findAllByEmployee(employee.get());
        vacationRepository.deleteAll(vacations);

        Collection<AttendanceSheet> attendanceSheets = attendanceSheetRepository.findAllByEmployee(employee.get());
        attendanceSheetRepository.deleteAll(attendanceSheets);

        employeeRepository.delete(employee.get());

        log.info("delete employee with id = {}", employeeId);
    }

    @Override
    @Transactional
    public Employee insertEmployee(Employee employee) {
        employee = employeeRepository.save(employee);
        log.info("insert new employee: {}", employee);
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
