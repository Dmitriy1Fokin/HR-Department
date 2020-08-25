package ru.fds.hrdepartment.service;

import ru.fds.hrdepartment.domain.SickLeave;

import java.util.Optional;

public interface SickLeaveService {
    Optional<SickLeave> getSickLeave(Long sickLeaveId);
    SickLeave insertSickLeave(SickLeave sickLeave);
    SickLeave updateSickLeave(SickLeave sickLeave);
}
