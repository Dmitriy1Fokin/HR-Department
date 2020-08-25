package ru.fds.hrdepartment.controller;

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
public class PositionController {

    private final PositionService positionService;
    private final PositionDtoConverter positionDtoConverter;

    public PositionController(PositionService positionService,
                              PositionDtoConverter positionDtoConverter) {
        this.positionService = positionService;
        this.positionDtoConverter = positionDtoConverter;
    }

    @GetMapping("{positionId}")
    public PositionDto getPosition(@PathVariable("positionId") Long positionId){
        return positionDtoConverter.toDto(positionService
                .getPosition(positionId).orElseThrow(() -> new NotFoundException("Position not found")));
    }

    @PostMapping("/new")
    public PositionDto insertPosition(@Valid @RequestBody PositionDto positionDto){
        Position position = positionDtoConverter.toEntity(positionDto);
        return positionDtoConverter.toDto(positionService.insertPosition(position));
    }

    @PutMapping("/update")
    public PositionDto updatePosition(@Valid @RequestBody PositionDto positionDto){
        Position position = positionDtoConverter.toEntity(positionDto);
        return positionDtoConverter.toDto(positionService.updatePosition(position));
    }
}
