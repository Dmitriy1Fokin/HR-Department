package ru.fds.hrdepartment.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.VacationSick;

import java.util.Collection;
import java.util.Optional;

public interface VacationSickRepository extends JpaRepository<VacationSick, Long> {
    Optional<VacationSick> findFirstByEmployee(Employee employee, Sort sort);
    Collection<VacationSick> findAllByEmployee(Employee employee);
}
