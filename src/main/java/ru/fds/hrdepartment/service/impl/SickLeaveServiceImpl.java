package ru.fds.hrdepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fds.hrdepartment.domain.SickLeave;
import ru.fds.hrdepartment.repository.SickLeaveRepository;
import ru.fds.hrdepartment.service.SickLeaveService;

import java.util.Optional;

@Slf4j
@Service
public class SickLeaveServiceImpl implements SickLeaveService {

    private final SickLeaveRepository sickLeaveRepository;

    public SickLeaveServiceImpl(SickLeaveRepository sickLeaveRepository) {
        this.sickLeaveRepository = sickLeaveRepository;
    }

    @Override
    public Optional<SickLeave> getSickLeave(Long sickLeaveId) {
        log.debug("getSickLeave. sickLeaveId: {}", sickLeaveId);
        return sickLeaveRepository.findById(sickLeaveId);
    }

    @Override
    public SickLeave insertSickLeave(SickLeave sickLeave) {
        sickLeave = sickLeaveRepository.save(sickLeave);
        log.info("setSickLeave. sickLeave: {}", sickLeave);
        return sickLeave;
    }

    @Override
    public SickLeave updateSickLeave(SickLeave sickLeave) {
        sickLeave = sickLeaveRepository.save(sickLeave);
        log.info("updateSickLeave. sickLeave: {}", sickLeave);
        return sickLeave;
    }
}
