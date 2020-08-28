package ru.fds.hrdepartment.controller;

import io.swagger.api.SickApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.hrdepartment.common.converter.dto.impl.SickLeaveDtoConverter;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.SickLeave;
import ru.fds.hrdepartment.dto.SickLeaveDto;
import ru.fds.hrdepartment.service.SickLeaveService;

import javax.validation.Valid;

@RestController
@RequestMapping("/sick")
public class SickLeaveController implements SickApi {

    private final SickLeaveService sickLeaveService;
    private final SickLeaveDtoConverter sickLeaveDtoConverter;

    public SickLeaveController(SickLeaveService sickLeaveService,
                               SickLeaveDtoConverter sickLeaveDtoConverter) {
        this.sickLeaveService = sickLeaveService;
        this.sickLeaveDtoConverter = sickLeaveDtoConverter;
    }

    @Override
    @GetMapping("/{sickId}")
    public ResponseEntity<SickLeaveDto> getSickLeave(@PathVariable("sickId") Long sickId){
        return ResponseEntity.ok(sickLeaveDtoConverter.toDto(sickLeaveService
                .getSickLeave(sickId).orElseThrow(() -> new NotFoundException("Sick leave not found"))));
    }

    @Override
    @PostMapping("/new")
    public ResponseEntity<SickLeaveDto> insertSickLeave(@Valid @RequestBody SickLeaveDto sickLeaveDto){
        SickLeave sickLeave = sickLeaveDtoConverter.toEntity(sickLeaveDto);
        return ResponseEntity.ok(sickLeaveDtoConverter.toDto(sickLeaveService.insertSickLeave(sickLeave)));
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<SickLeaveDto> updateSickLeave(@Valid @RequestBody SickLeaveDto sickLeaveDto){
        SickLeave sickLeave = sickLeaveDtoConverter.toEntity(sickLeaveDto);
        return ResponseEntity.ok(sickLeaveDtoConverter.toDto(sickLeaveService.updateSickLeave(sickLeave)));
    }
}
