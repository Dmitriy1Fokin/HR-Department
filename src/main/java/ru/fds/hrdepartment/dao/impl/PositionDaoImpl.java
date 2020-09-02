package ru.fds.hrdepartment.dao.impl;

import org.springframework.stereotype.Component;
import ru.fds.hrdepartment.dao.PositionDao;
import ru.fds.hrdepartment.domain.Position;
import ru.fds.hrdepartment.utils.HiberUtils;

import javax.persistence.EntityManager;
import java.util.Optional;

@Component
public class PositionDaoImpl implements PositionDao {

    private final EntityManager entityManager = HiberUtils.getEntityManager();

    @Override
    public Optional<Position> findById(Long positionId){
        return Optional.ofNullable(entityManager.find(Position.class, positionId));
    }

    @Override
    public Position save(Position position){
        entityManager.getTransaction().begin();
        entityManager.persist(position);
        entityManager.getTransaction().commit();
        return position;
    }

    @Override
    public Position update(Position position){
        entityManager.getTransaction().begin();
        entityManager.merge(position);
        entityManager.getTransaction().commit();
        return position;
    }
}
