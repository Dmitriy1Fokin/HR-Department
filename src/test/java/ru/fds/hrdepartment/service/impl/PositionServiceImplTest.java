package ru.fds.hrdepartment.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.fds.hrdepartment.TestUtils;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.dao.PositionDao;
import ru.fds.hrdepartment.domain.Position;
import ru.fds.hrdepartment.service.PositionService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PositionServiceImplTest {

    @Autowired
    private PositionService positionService;

    @MockBean
    private PositionDao positionDao;

    @BeforeEach
    void setUp() {
        Position position= TestUtils.getPosition();

        Mockito.when(positionDao.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(position));
        Mockito.when(positionDao.save(Mockito.any(Position.class)))
                .thenReturn(position);
        Mockito.when(positionDao.update(Mockito.any(Position.class)))
                .thenReturn(position);
    }

    @Test
    void getPosition() {
        Optional<Position> position = positionService.getPosition(Mockito.anyLong());

        position.ifPresentOrElse(position1 -> assertEquals(TestUtils.getPosition().getId(), position1.getId()),
                () -> {throw new NotFoundException("Position not found");});
    }

    @Test
    void insertPosition() {
        Position position = positionService.insertPosition(TestUtils.getPosition());

        assertEquals(TestUtils.getPosition().getId(), position.getId());
    }

    @Test
    void updatePosition() {
        Position position = positionService.updatePosition(TestUtils.getPosition());

        assertEquals(TestUtils.getPosition().getId(), position.getId());
    }
}