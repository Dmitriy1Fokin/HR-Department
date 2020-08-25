package ru.fds.hrdepartment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class EmployeeDto {

    private Long id;

    @NotNull(message = "Обязательно для заполнения")
    private String firstName;

    @NotNull(message = "Обязательно для заполнения")
    private String lastName;

    @NotNull(message = "Обязательно для заполнения")
    private LocalDate dateIn;

    private LocalDate dateOut;

    @NotNull(message = "Обязательно для заполнения")
    private Long departmentId;

    @NotNull(message = "Обязательно для заполнения")
    private Long positionId;
}
