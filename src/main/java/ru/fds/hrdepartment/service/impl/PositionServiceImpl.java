package ru.fds.hrdepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fds.hrdepartment.domain.Position;
import ru.fds.hrdepartment.repository.PositionRepository;
import ru.fds.hrdepartment.service.PositionService;

import java.util.Optional;

@Slf4j
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

    @Override
    @Transactional
    public Position insertPosition(Position position) {
        position = positionRepository.save(position);
        log.info("insert new position: {}", position);
        return position;
    }

    @Override
    @Transactional
    public Position updatePosition(Position position) {
        position = positionRepository.save(position);
        log.info("update position: {}", position);
        return position;
    }
}
