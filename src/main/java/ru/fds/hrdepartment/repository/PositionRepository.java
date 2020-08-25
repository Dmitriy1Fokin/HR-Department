package ru.fds.hrdepartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fds.hrdepartment.domain.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
