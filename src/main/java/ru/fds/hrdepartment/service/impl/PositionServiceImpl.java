package ru.fds.hrdepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fds.hrdepartment.dao.PositionDao;
import ru.fds.hrdepartment.domain.Position;
import ru.fds.hrdepartment.service.PositionService;

import java.util.Optional;

@Slf4j
@Service
public class PositionServiceImpl implements PositionService {

    private final PositionDao positionDao;

    public PositionServiceImpl(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Override
    public Optional<Position> getPosition(Long positionId) {
        log.debug("getPosition. positionId: {}", positionId);
        return positionDao.findById(positionId);
    }

    @Override
    @Transactional
    public Position insertPosition(Position position) {
        position = positionDao.save(position);
        log.info("insert new position: {}", position);
        return position;
    }

    @Override
    @Transactional
    public Position updatePosition(Position position) {
        position = positionDao.update(position);
        log.info("update position: {}", position);
        return position;
    }
}
