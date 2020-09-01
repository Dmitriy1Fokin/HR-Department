package ru.fds.hrdepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fds.hrdepartment.dao.AttendanceSheetDao;
import ru.fds.hrdepartment.dao.EmployeeDao;
import ru.fds.hrdepartment.dao.SickLeaveDao;
import ru.fds.hrdepartment.dao.VacationDao;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.helpertype.TypeOfAttendance;
import ru.fds.hrdepartment.service.EmployeeService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;
    private final AttendanceSheetDao attendanceSheetDao;
    private final VacationDao vacationDao;
    private final SickLeaveDao sickLeaveDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao,
                               AttendanceSheetDao attendanceSheetDao,
                               VacationDao vacationDao,
                               SickLeaveDao sickLeaveDao) {
        this.employeeDao = employeeDao;
        this.attendanceSheetDao = attendanceSheetDao;
        this.sickLeaveDao = sickLeaveDao;
        this.vacationDao = vacationDao;
    }

    @Override
    public Optional<Employee> getEmployee(Long employeeId) {
        log.debug("getEmployee. EmployeeId = {}", employeeId);
        return employeeDao.findById(employeeId);
    }

    @Override
    public Integer getWorkDaysByEmployee(Long employeeId) {
        log.debug("getWorkDaysByEmployee. employeeId = {}", employeeId);
        return employeeDao.getWorkDaysByEmployee(employeeId);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long employeeId) {
        getEmployee(employeeId).ifPresent(employee -> {
            vacationDao.deleteAllByEmployee(employee);
            sickLeaveDao.deleteAllByEmployee(employee);
            attendanceSheetDao.deleteAllByEmployee(employee);
            employeeDao.delete(employee.getId());
        });

        log.info("deleted employee with id = {}", employeeId);
    }

    @Override
    @Transactional
    public Employee insertEmployee(Employee employee) {
        employee = employeeDao.save(employee);
        log.info("insert new employee: {}", employee);
        return employee;
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) {
        employee = employeeDao.update(employee);
        log.info("update employee: {}", employee);
        return employee;
    }

    @Override
    public Collection<Employee> getEmployeeByTypeOfAttendance(LocalDate date, TypeOfAttendance typeOfAttendance) {
        log.debug("getEmployeeByTypeOfAttendance. date: {}, typeOfAttendance:{}", date, typeOfAttendance);
        Collection<Employee> employees = employeeDao.getEmployeeByTypeOfAttendance(date, typeOfAttendance);
        log.debug("getEmployeeByTypeOfAttendance. employees: {}", employees);
        return employees;
    }
}
