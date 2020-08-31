package ru.fds.hrdepartment.controller;

import io.swagger.api.VacationApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.hrdepartment.common.converter.dto.impl.VacationDtoConverter;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.domain.Vacation;
import ru.fds.hrdepartment.dto.VacationDto;
import ru.fds.hrdepartment.service.VacationService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class VacationController implements VacationApi {

    private final VacationService vacationService;
    private final VacationDtoConverter vacationDtoConverter;

    public VacationController(VacationService vacationService,
                              VacationDtoConverter vacationDtoConverter) {
        this.vacationService = vacationService;
        this.vacationDtoConverter = vacationDtoConverter;
    }

    @Override
    public ResponseEntity<VacationDto> getVacation(Long vacationId){
        return ResponseEntity.ok(vacationDtoConverter.toDto(vacationService
                .getVacation(vacationId).orElseThrow(() -> new NotFoundException("Vacation not found"))));
    }

    @Override
    public ResponseEntity<VacationDto> insertVacation(@Valid VacationDto vacationDto){
        Vacation vacation = vacationDtoConverter.toEntity(vacationDto);
        return new ResponseEntity<>(vacationDtoConverter.toDto(vacationService.insertVacation(vacation)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<VacationDto> updateVacation(@Valid VacationDto vacationDto){
        Vacation vacation = vacationDtoConverter.toEntity(vacationDto);
        return new ResponseEntity<>(vacationDtoConverter.toDto(vacationService.updateVacation(vacation)), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<VacationDto> getLastVacationByEmployee(@NotNull @Valid Long employeeId){
        return ResponseEntity.ok(vacationDtoConverter.toDto(vacationService
                .getLastVacationByEmployee(employeeId).orElseThrow(() -> new NotFoundException("Vacation not found"))));
    }
}
