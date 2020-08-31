package ru.fds.hrdepartment.controller;

import io.swagger.api.PositionApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.hrdepartment.common.converter.dto.impl.PositionDtoConverter;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.Position;
import ru.fds.hrdepartment.dto.PositionDto;
import ru.fds.hrdepartment.service.PositionService;

import javax.validation.Valid;

@RestController
public class PositionController implements PositionApi {

    private final PositionService positionService;
    private final PositionDtoConverter positionDtoConverter;

    public PositionController(PositionService positionService,
                              PositionDtoConverter positionDtoConverter) {
        this.positionService = positionService;
        this.positionDtoConverter = positionDtoConverter;
    }

    @Override
    public ResponseEntity<PositionDto> getPosition(Long positionId){
        return ResponseEntity.ok(positionDtoConverter.toDto(positionService
                .getPosition(positionId).orElseThrow(() -> new NotFoundException("Position not found"))));
    }

    @Override
    public ResponseEntity<PositionDto> insertPosition(@Valid PositionDto positionDto){
        Position position = positionDtoConverter.toEntity(positionDto);
        return new ResponseEntity<>(positionDtoConverter.toDto(positionService.insertPosition(position)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PositionDto> updatePosition(@Valid PositionDto positionDto){
        Position position = positionDtoConverter.toEntity(positionDto);
        return new ResponseEntity<>(positionDtoConverter.toDto(positionService.updatePosition(position)), HttpStatus.ACCEPTED);
    }
}
