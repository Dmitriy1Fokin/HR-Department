package ru.fds.hrdepartment.service.impl;

import org.springframework.stereotype.Service;
import ru.fds.hrdepartment.domain.Position;
import ru.fds.hrdepartment.repository.PositionRepository;
import ru.fds.hrdepartment.service.PositionService;

import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Optional<Position> getPosition(Long positionId) {
        return positionRepository.findById(positionId);
    }
}
