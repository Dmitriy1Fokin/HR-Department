package ru.fds.hrdepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fds.hrdepartment.common.exception.ConditionFailException;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.Vacation;
import ru.fds.hrdepartment.repository.EmployeeRepository;
import ru.fds.hrdepartment.repository.VacationRepository;
import ru.fds.hrdepartment.service.VacationService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class VacationServiceImpl implements VacationService {

    private final VacationRepository vacationRepository;
    private final EmployeeRepository employeeRepository;

    public VacationServiceImpl(VacationRepository vacationRepository,
                               EmployeeRepository employeeRepository) {
        this.vacationRepository = vacationRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<Vacation> getVacation(Long vacationId) {
        log.debug("getVacation. vacationId: {}", vacationId);
        return vacationRepository.findById(vacationId);
    }

    @Override
    @Transactional
    public Vacation insertVacation(Vacation vacation) {
        Collection<Employee> employees = employeeRepository
                .findAllByDepartmentAndPosition(vacation.getEmployee().getDepartment(),
                        vacation.getEmployee().getPosition());

        if(isCanLeaveInVacation(employees)){
            vacation = vacationRepository.save(vacation);
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
            int countEmp = employeeRepository.countOfEmployeesInVacation(employeesInDepartmentInThisPosition, LocalDate.now());
            return employeesInDepartmentInThisPosition.size() - countEmp >= 2;
        }
    }

    @Override
    @Transactional
    public Vacation updateVacation(Vacation vacation) {
        vacation = vacationRepository.save(vacation);
        log.info("updateVacation. vacation: {}", vacation);
        return vacation;
    }

    @Override
    public Optional<Vacation> getLastVacationByEmployee(Long employeeId) {
        log.debug("getLastVacationByEmployee. employeeId: {}", employeeId);

        Optional<Vacation> vacation = employeeRepository.findById(employeeId)
                .flatMap(employee -> vacationRepository
                        .findFirstByEmployee(employee, Sort.by(Sort.Direction.DESC, "dateStart")));

        log.debug("getLastVacationByEmployee. vacation: {}", vacation);

        return vacation;
    }
}
