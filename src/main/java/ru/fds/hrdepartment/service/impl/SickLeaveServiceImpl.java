package ru.fds.hrdepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fds.hrdepartment.common.exception.ConditionFailException;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.SickLeave;
import ru.fds.hrdepartment.repository.EmployeeRepository;
import ru.fds.hrdepartment.repository.SickLeaveRepository;
import ru.fds.hrdepartment.service.SickLeaveService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class SickLeaveServiceImpl implements SickLeaveService {

    private final SickLeaveRepository sickLeaveRepository;
    private final EmployeeRepository employeeRepository;

    public SickLeaveServiceImpl(SickLeaveRepository sickLeaveRepository,
                                EmployeeRepository employeeRepository) {
        this.sickLeaveRepository = sickLeaveRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<SickLeave> getSickLeave(Long sickLeaveId) {
        log.debug("getSickLeave. sickLeaveId: {}", sickLeaveId);
        return sickLeaveRepository.findById(sickLeaveId);
    }

    @Override
    public SickLeave insertSickLeave(SickLeave sickLeave) {
        Collection<Employee> employees = employeeRepository
                .findAllByDepartmentAndPosition(sickLeave.getEmployee().getDepartment(),
                        sickLeave.getEmployee().getPosition());

        if(isCanLeaveInSickLeave(employees)){
            sickLeave = sickLeaveRepository.save(sickLeave);
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
            int countEmp = employeeRepository.countOfSickEmployees(employeesInDepartmentInThisPosition, LocalDate.now());
            return employeesInDepartmentInThisPosition.size() - countEmp >= 2;
        }
    }

    @Override
    public SickLeave updateSickLeave(SickLeave sickLeave) {
        sickLeave = sickLeaveRepository.save(sickLeave);
        log.info("updateSickLeave. sickLeave: {}", sickLeave);
        return sickLeave;
    }
}
