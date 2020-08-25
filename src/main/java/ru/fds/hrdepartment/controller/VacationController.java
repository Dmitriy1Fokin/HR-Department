package ru.fds.hrdepartment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.hrdepartment.common.converter.dto.impl.VacationDtoConverter;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.Vacation;
import ru.fds.hrdepartment.dto.VacationDto;
import ru.fds.hrdepartment.service.VacationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/vacation")
public class VacationController {

    private final VacationService vacationService;
    private final VacationDtoConverter vacationDtoConverter;

    public VacationController(VacationService vacationService,
                              VacationDtoConverter vacationDtoConverter) {
        this.vacationService = vacationService;
        this.vacationDtoConverter = vacationDtoConverter;
    }

    @GetMapping("/{vacationId}")
    public VacationDto getVacation(@PathVariable("vacationId") Long vacationId){
        return vacationDtoConverter.toDto(vacationService
                .getVacation(vacationId).orElseThrow(() -> new NotFoundException("Vacation not found")));
    }

    @PostMapping("/new")
    public VacationDto insertVacation(@Valid @RequestBody VacationDto vacationDto){
        Vacation vacation = vacationDtoConverter.toEntity(vacationDto);
        return vacationDtoConverter.toDto(vacationService.insertVacation(vacation));
    }

    @PutMapping("/update")
    public VacationDto updateVacation(@Valid @RequestBody VacationDto vacationDto){
        Vacation vacation = vacationDtoConverter.toEntity(vacationDto);
        return vacationDtoConverter.toDto(vacationService.updateVacation(vacation));
    }

    @GetMapping("/employee_last")
    public VacationDto getLastVacationByEmployee(@RequestParam("employeeId") Long employeeId){
        return vacationDtoConverter.toDto(vacationService
                .getLastVacationByEmployee(employeeId).orElseThrow(() -> new NotFoundException("Vacation not found")));
    }
}
