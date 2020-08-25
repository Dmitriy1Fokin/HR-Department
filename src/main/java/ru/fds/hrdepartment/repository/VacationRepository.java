package ru.fds.hrdepartment.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.Vacation;

import java.util.Collection;
import java.util.Optional;

public interface VacationRepository extends JpaRepository<Vacation, Long> {
    Optional<Vacation> findFirstByEmployee(Employee employee, Sort sort);
    Collection<Vacation> findAllByEmployee(Employee employee);
}
