package ru.fds.hrdepartment.controller;

import io.swagger.api.SickApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.hrdepartment.common.converter.dto.impl.SickLeaveDtoConverter;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.SickLeave;
import ru.fds.hrdepartment.dto.SickLeaveDto;
import ru.fds.hrdepartment.service.SickLeaveService;

import javax.validation.Valid;

@RestController
public class SickLeaveController implements SickApi {

    private final SickLeaveService sickLeaveService;
    private final SickLeaveDtoConverter sickLeaveDtoConverter;

    public SickLeaveController(SickLeaveService sickLeaveService,
                               SickLeaveDtoConverter sickLeaveDtoConverter) {
        this.sickLeaveService = sickLeaveService;
        this.sickLeaveDtoConverter = sickLeaveDtoConverter;
    }

    @Override
    public ResponseEntity<SickLeaveDto> getSickLeave(Long sickId){
        return ResponseEntity.ok(sickLeaveDtoConverter.toDto(sickLeaveService
                .getSickLeave(sickId).orElseThrow(() -> new NotFoundException("Sick leave not found"))));
    }

    @Override
    public ResponseEntity<SickLeaveDto> insertSickLeave(@Valid SickLeaveDto sickLeaveDto){
        SickLeave sickLeave = sickLeaveDtoConverter.toEntity(sickLeaveDto);
        return new ResponseEntity<>(sickLeaveDtoConverter.toDto(sickLeaveService.insertSickLeave(sickLeave)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SickLeaveDto> updateSickLeave(@Valid SickLeaveDto sickLeaveDto){
        SickLeave sickLeave = sickLeaveDtoConverter.toEntity(sickLeaveDto);
        return new ResponseEntity<>(sickLeaveDtoConverter.toDto(sickLeaveService.updateSickLeave(sickLeave)), HttpStatus.ACCEPTED);
    }
}
