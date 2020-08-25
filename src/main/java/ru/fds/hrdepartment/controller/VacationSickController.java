package ru.fds.hrdepartment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.hrdepartment.common.converter.dto.impl.VacationSickDtoConverter;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.VacationSick;
import ru.fds.hrdepartment.dto.VacationSickDto;
import ru.fds.hrdepartment.service.VacationSickService;

import javax.validation.Valid;

@RestController
@RequestMapping("/vacation")
public class VacationSickController {

    private final VacationSickService vacationSickService;
    private final VacationSickDtoConverter vacationSickDtoConverter;

    public VacationSickController(VacationSickService vacationSickService,
                                  VacationSickDtoConverter vacationSickDtoConverter) {
        this.vacationSickService = vacationSickService;
        this.vacationSickDtoConverter = vacationSickDtoConverter;
    }

    @GetMapping("/{vacationId}")
    public VacationSickDto getVacationSick(@PathVariable("vacationId") Long vacationId){
        return vacationSickDtoConverter.toDto(vacationSickService
                .getVacationSick(vacationId).orElseThrow(() -> new NotFoundException("Vacation not found")));
    }

    @PostMapping("/new")
    public VacationSickDto insertVacation(@Valid @RequestBody VacationSickDto vacationSickDto){
        VacationSick vacationSick = vacationSickDtoConverter.toEntity(vacationSickDto);
        return vacationSickDtoConverter.toDto(vacationSickService.setVacationSick(vacationSick));
    }

    @PutMapping("/update")
    public VacationSickDto updateVacation(@Valid @RequestBody VacationSickDto vacationSickDto){
        VacationSick vacationSick = vacationSickDtoConverter.toEntity(vacationSickDto);
        return vacationSickDtoConverter.toDto(vacationSickService.updateVacationSick(vacationSick));
    }

    @GetMapping("/employee_last")
    public VacationSickDto getLastVacationByEmployee(@RequestParam("employeeId") Long employeeId){
        return vacationSickDtoConverter.toDto(vacationSickService
                .getLastVacationByEmployee(employeeId).orElseThrow(() -> new NotFoundException("Vacation not found")));
    }
}
