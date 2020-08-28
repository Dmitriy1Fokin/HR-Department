package ru.fds.hrdepartment.controller;

import io.swagger.api.PositionApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.hrdepartment.common.converter.dto.impl.PositionDtoConverter;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.Position;
import ru.fds.hrdepartment.dto.PositionDto;
import ru.fds.hrdepartment.service.PositionService;

import javax.validation.Valid;

@RestController
@RequestMapping("/position")
public class PositionController implements PositionApi {

    private final PositionService positionService;
    private final PositionDtoConverter positionDtoConverter;

    public PositionController(PositionService positionService,
                              PositionDtoConverter positionDtoConverter) {
        this.positionService = positionService;
        this.positionDtoConverter = positionDtoConverter;
    }

    @Override
    @GetMapping("{positionId}")
    public ResponseEntity<PositionDto> getPosition(@PathVariable("positionId") Long positionId){
        return ResponseEntity.ok(positionDtoConverter.toDto(positionService
                .getPosition(positionId).orElseThrow(() -> new NotFoundException("Position not found"))));
    }

    @Override
    @PostMapping("/new")
    public ResponseEntity<PositionDto> insertPosition(@Valid @RequestBody PositionDto positionDto){
        Position position = positionDtoConverter.toEntity(positionDto);
        return ResponseEntity.ok(positionDtoConverter.toDto(positionService.insertPosition(position)));
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<PositionDto> updatePosition(@Valid @RequestBody PositionDto positionDto){
        Position position = positionDtoConverter.toEntity(positionDto);
        return ResponseEntity.ok(positionDtoConverter.toDto(positionService.updatePosition(position)));
    }
}
