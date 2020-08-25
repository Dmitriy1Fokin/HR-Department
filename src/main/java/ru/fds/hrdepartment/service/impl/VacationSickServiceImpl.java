package ru.fds.hrdepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.VacationSick;
import ru.fds.hrdepartment.repository.EmployeeRepository;
import ru.fds.hrdepartment.repository.VacationSickRepository;
import ru.fds.hrdepartment.service.VacationSickService;

import java.util.Optional;

@Slf4j
@Service
public class VacationSickServiceImpl implements VacationSickService {

    private final VacationSickRepository vacationSickRepository;
    private final EmployeeRepository employeeRepository;

    public VacationSickServiceImpl(VacationSickRepository vacationSickRepository,
                                   EmployeeRepository employeeRepository) {
        this.vacationSickRepository = vacationSickRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<VacationSick> getVacationSick(Long vacationSickId) {
        log.debug("getVacationSick. vacationSickId: {}", vacationSickId);
        return vacationSickRepository.findById(vacationSickId);
    }

    @Override
    @Transactional
    public VacationSick setVacationSick(VacationSick vacationSick) {
        vacationSick = vacationSickRepository.save(vacationSick);
        log.info("setVacationSick. vacationSick: {}", vacationSick);
        return vacationSick;
    }

    @Override
    @Transactional
    public VacationSick updateVacationSick(VacationSick vacationSick) {
        vacationSick = vacationSickRepository.save(vacationSick);
        log.info("updateVacationSick. vacationSick: {}", vacationSick);
        return vacationSick;
    }

    @Override
    public Optional<VacationSick> getLastVacationByEmployee(Long employeeId) {
        log.debug("getLastVacationByEmployee. employeeId: {}", employeeId);

        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isEmpty()){
            log.debug("getLastVacationByEmployee. vacationSick: null");
            return Optional.empty();
        }

        Optional<VacationSick> vacationSick = vacationSickRepository
                .findFirstByEmployee(employee.get(), Sort.by(Sort.Direction.DESC, "dateStart"));
        log.debug("getLastVacationByEmployee. vacationSick: {}", vacationSick);
        return vacationSick;
    }
}
