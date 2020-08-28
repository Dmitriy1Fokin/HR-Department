package ru.fds.hrdepartment.controller;

import io.swagger.api.VacationApi;
import org.springframework.http.ResponseEntity;
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
public class VacationController implements VacationApi {

    private final VacationService vacationService;
    private final VacationDtoConverter vacationDtoConverter;

    public VacationController(VacationService vacationService,
                              VacationDtoConverter vacationDtoConverter) {
        this.vacationService = vacationService;
        this.vacationDtoConverter = vacationDtoConverter;
    }

    @Override
    @GetMapping("/{vacationId}")
    public ResponseEntity<VacationDto> getVacation(@PathVariable("vacationId") Long vacationId){
        return ResponseEntity.ok(vacationDtoConverter.toDto(vacationService
                .getVacation(vacationId).orElseThrow(() -> new NotFoundException("Vacation not found"))));
    }

    @Override
    @PostMapping("/new")
    public ResponseEntity<VacationDto> insertVacation(@Valid @RequestBody VacationDto vacationDto){
        Vacation vacation = vacationDtoConverter.toEntity(vacationDto);
        return ResponseEntity.ok(vacationDtoConverter.toDto(vacationService.insertVacation(vacation)));
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<VacationDto> updateVacation(@Valid @RequestBody VacationDto vacationDto){
        Vacation vacation = vacationDtoConverter.toEntity(vacationDto);
        return ResponseEntity.ok(vacationDtoConverter.toDto(vacationService.updateVacation(vacation)));
    }

    @Override
    @GetMapping("/employee_last")
    public ResponseEntity<VacationDto> getLastVacationByEmployee(@RequestParam("employeeId") Long employeeId){
        return ResponseEntity.ok(vacationDtoConverter.toDto(vacationService
                .getLastVacationByEmployee(employeeId).orElseThrow(() -> new NotFoundException("Vacation not found"))));
    }
}
