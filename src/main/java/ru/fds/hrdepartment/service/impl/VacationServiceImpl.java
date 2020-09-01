package ru.fds.hrdepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fds.hrdepartment.common.exception.ConditionFailException;
import ru.fds.hrdepartment.dao.EmployeeDao;
import ru.fds.hrdepartment.dao.VacationDao;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.Vacation;
import ru.fds.hrdepartment.service.VacationService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class VacationServiceImpl implements VacationService {

    private final VacationDao vacationDao;
    private final EmployeeDao employeeDao;

    public VacationServiceImpl(VacationDao vacationDao,
                               EmployeeDao employeeDao) {
        this.vacationDao = vacationDao;
        this.employeeDao = employeeDao;
    }

    @Override
    public Optional<Vacation> getVacation(Long vacationId) {
        log.debug("getVacation. vacationId: {}", vacationId);
        return vacationDao.findById(vacationId);
    }

    @Override
    @Transactional
    public Vacation insertVacation(Vacation vacation) {
        Collection<Employee> employees = employeeDao
                .findAllByDepartmentAndPosition(vacation.getEmployee().getDepartment(),
                        vacation.getEmployee().getPosition());

        if(isCanLeaveInVacation(employees)){
            vacation = vacationDao.save(vacation);
            log.info("insertVacation. vacation: {}", vacation);
            return vacation;
        }else{
            throw new ConditionFailException("Employee can't leave because there must be 1 or more people left in the Department in this position");
        }
    }

    private boolean isCanLeaveInVacation(Collection<Employee> employeesInDepartmentInThisPosition){
        if(employeesInDepartmentInThisPosition.size() < 2){
            return false;
        }else{
            int countEmp = employeeDao.countOfEmployeesInVacation(employeesInDepartmentInThisPosition, LocalDate.now());
            return employeesInDepartmentInThisPosition.size() - countEmp >= 2;
        }
    }

    @Override
    @Transactional
    public Vacation updateVacation(Vacation vacation) {
        vacation = vacationDao.update(vacation);
        log.info("updateVacation. vacation: {}", vacation);
        return vacation;
    }

    @Override
    public Optional<Vacation> getLastVacationByEmployee(Long employeeId) {
        log.debug("getLastVacationByEmployee. employeeId: {}", employeeId);

        Optional<Vacation> vacation = employeeDao.findById(employeeId)
                .flatMap(vacationDao::findLastVacationByEmployee);

        log.debug("getLastVacationByEmployee. vacation: {}", vacation);

        return vacation;
    }
}
