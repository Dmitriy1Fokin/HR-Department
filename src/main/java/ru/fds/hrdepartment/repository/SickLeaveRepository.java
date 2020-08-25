package ru.fds.hrdepartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fds.hrdepartment.domain.SickLeave;

public interface SickLeaveRepository extends JpaRepository<SickLeave, Long> {
}
