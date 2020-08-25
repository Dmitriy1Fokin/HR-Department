package ru.fds.hrdepartment.controller;

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
public class SickLeaveController {

    private final SickLeaveService sickLeaveService;
    private final SickLeaveDtoConverter sickLeaveDtoConverter;

    public SickLeaveController(SickLeaveService sickLeaveService,
                               SickLeaveDtoConverter sickLeaveDtoConverter) {
        this.sickLeaveService = sickLeaveService;
        this.sickLeaveDtoConverter = sickLeaveDtoConverter;
    }

    @GetMapping("/{sickId}")
    public SickLeaveDto getSickLeave(@PathVariable("sickId") Long sickId){
        return sickLeaveDtoConverter.toDto(sickLeaveService
                .getSickLeave(sickId).orElseThrow(() -> new NotFoundException("Sick leave not found")));
    }

    @PostMapping("/new")
    public SickLeaveDto insertSickLeave(@Valid @RequestBody SickLeaveDto sickLeaveDto){
        SickLeave sickLeave = sickLeaveDtoConverter.toEntity(sickLeaveDto);
        return sickLeaveDtoConverter.toDto(sickLeaveService.insertSickLeave(sickLeave));
    }

    @PutMapping("/update")
    public SickLeaveDto updateSickLeave(@Valid @RequestBody SickLeaveDto sickLeaveDto){
        SickLeave sickLeave = sickLeaveDtoConverter.toEntity(sickLeaveDto);
        return sickLeaveDtoConverter.toDto(sickLeaveService.updateSickLeave(sickLeave));
    }
}
