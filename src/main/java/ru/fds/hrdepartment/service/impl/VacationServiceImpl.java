package ru.fds.hrdepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.Vacation;
import ru.fds.hrdepartment.repository.EmployeeRepository;
import ru.fds.hrdepartment.repository.VacationRepository;
import ru.fds.hrdepartment.service.VacationService;

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
        vacation = vacationRepository.save(vacation);
        log.info("insertVacation. vacation: {}", vacation);
        return vacation;
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

        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isEmpty()){
            log.debug("getLastVacationByEmployee. vacation: null");
            return Optional.empty();
        }

        Optional<Vacation> vacationSick = vacationRepository
                .findFirstByEmployee(employee.get(), Sort.by(Sort.Direction.DESC, "dateStart"));
        log.debug("getLastVacationByEmployee. vacation: {}", vacationSick);
        return vacationSick;
    }
}
