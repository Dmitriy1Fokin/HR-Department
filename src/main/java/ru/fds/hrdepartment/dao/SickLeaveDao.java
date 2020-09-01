package ru.fds.hrdepartment.dao;

import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.SickLeave;

import java.util.List;
import java.util.Optional;

public interface SickLeaveDao {
    Optional<SickLeave> findById(Long sickLeaveId);
    List<SickLeave> findAllByEmployee(Employee employee);
    SickLeave save(SickLeave sickLeave);
    SickLeave update(SickLeave sickLeave);
    void deleteAllByEmployee(Employee employee);
}
