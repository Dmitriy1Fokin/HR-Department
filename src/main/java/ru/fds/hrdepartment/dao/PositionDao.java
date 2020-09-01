package ru.fds.hrdepartment.dao;

import ru.fds.hrdepartment.domain.Position;

import java.util.Optional;

public interface PositionDao {
    Optional<Position> findById(Long positionId);
    Position save(Position position);
    Position update(Position position);
}
