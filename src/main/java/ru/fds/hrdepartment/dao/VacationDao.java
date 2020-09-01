package ru.fds.hrdepartment.dao;

import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.Vacation;

import java.util.List;
import java.util.Optional;

public interface VacationDao {
    Optional<Vacation> findById(Long vacationId);
    List<Vacation> findAllByEmployee(Employee employee);
    Optional<Vacation> findLastVacationByEmployee(Employee employee);
    Vacation save(Vacation vacation);
    Vacation update(Vacation vacation);
    void deleteAllByEmployee(Employee employee);
}
