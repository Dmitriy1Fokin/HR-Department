package ru.fds.hrdepartment.common.converter.dto.impl;

import org.springframework.stereotype.Component;
import ru.fds.hrdepartment.common.converter.dto.ConverterDto;
import ru.fds.hrdepartment.domain.Position;
import ru.fds.hrdepartment.dto.PositionDto;

@Component
public class PositionDtoConverter implements ConverterDto<Position, PositionDto> {

    @Override
    public Position toEntity(PositionDto dto) {
        return new Position(dto.getId(), dto.getName());
    }

    @Override
    public PositionDto toDto(Position entity) {
        return new PositionDto(entity.getId(), entity.getName());
    }
}
