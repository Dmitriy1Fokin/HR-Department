package ru.fds.hrdepartment.service;

import ru.fds.hrdepartment.domain.VacationSick;

import java.util.Optional;

public interface VacationSickService {
    Optional<VacationSick> getVacationSick(Long vacationSickId);
    VacationSick setVacationSick(VacationSick vacationSick);
    VacationSick updateVacationSick(VacationSick vacationSick);
    Optional<VacationSick> getLastVacationByEmployee(Long employeeId);
}
