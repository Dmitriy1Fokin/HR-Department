package ru.fds.hrdepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fds.hrdepartment.common.exception.ConditionFailException;
import ru.fds.hrdepartment.dao.EmployeeDao;
import ru.fds.hrdepartment.dao.SickLeaveDao;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.SickLeave;
import ru.fds.hrdepartment.service.SickLeaveService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class SickLeaveServiceImpl implements SickLeaveService {

    private final SickLeaveDao sickLeaveDao;
    private final EmployeeDao employeeDao;

    public SickLeaveServiceImpl(SickLeaveDao sickLeaveDao,
                                EmployeeDao employeeDao) {
        this.sickLeaveDao = sickLeaveDao;
        this.employeeDao = employeeDao;
    }

    @Override
    public Optional<SickLeave> getSickLeave(Long sickLeaveId) {
        log.debug("getSickLeave. sickLeaveId: {}", sickLeaveId);
        return sickLeaveDao.findById(sickLeaveId);
    }

    @Override
    public SickLeave insertSickLeave(SickLeave sickLeave) {
        Collection<Employee> employees = employeeDao
                .findAllByDepartmentAndPosition(sickLeave.getEmployee().getDepartment(),
                        sickLeave.getEmployee().getPosition());

        if(isCanLeaveInSickLeave(employees)){
            sickLeave = sickLeaveDao.save(sickLeave);
            log.info("insertSickLeave. sickLeave: {}", sickLeave);
            return sickLeave;
        }else{
            throw new ConditionFailException("Employee can't leave because there must be 1 or more people left in the Department in this position");
        }
    }

    private boolean isCanLeaveInSickLeave(Collection<Employee> employeesInDepartmentInThisPosition){
        if(employeesInDepartmentInThisPosition.size() < 2){
            return false;
        }else{
            int countEmp = employeeDao.countOfSickEmployees(employeesInDepartmentInThisPosition, LocalDate.now());
            return employeesInDepartmentInThisPosition.size() - countEmp >= 2;
        }
    }

    @Override
    public SickLeave updateSickLeave(SickLeave sickLeave) {
        sickLeave = sickLeaveDao.update(sickLeave);
        log.info("updateSickLeave. sickLeave: {}", sickLeave);
        return sickLeave;
    }
}
