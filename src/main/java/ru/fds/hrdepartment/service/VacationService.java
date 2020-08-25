package ru.fds.hrdepartment.service;

import ru.fds.hrdepartment.domain.Vacation;

import java.util.Optional;

public interface VacationService {
    Optional<Vacation> getVacation(Long vacationId);
    Vacation insertVacation(Vacation vacation);
    Vacation updateVacation(Vacation vacation);
    Optional<Vacation> getLastVacationByEmployee(Long employeeId);
}
