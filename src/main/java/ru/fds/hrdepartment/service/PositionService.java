package ru.fds.hrdepartment.service;

import ru.fds.hrdepartment.domain.Position;

import java.util.Optional;

public interface PositionService {
    Optional<Position> getPosition(Long positionId);
    Position insertPosition(Position position);
    Position updatePosition(Position position);
}
