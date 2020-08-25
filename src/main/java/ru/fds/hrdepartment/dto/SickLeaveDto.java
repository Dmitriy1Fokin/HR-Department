package ru.fds.hrdepartment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.fds.hrdepartment.domain.helpertype.TypeOfSickLeave;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class SickLeaveDto {

    private Long id;

    @NotNull(message = "Обязательно для заполнения")
    private Long employeeId;

    @NotNull(message = "Обязательно для заполнения")
    private LocalDate dateStart;

    private LocalDate dateEnd;

    @NotNull(message = "Обязательно для заполнения")
    private TypeOfSickLeave typeOfSickLeave;
}
