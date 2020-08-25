package ru.fds.hrdepartment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ru.fds.hrdepartment.domain.helpertype.TypeOfAttendance;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class AttendanceSheetDto {

    private Long id;

    @NotNull(message = "Обязательно для заполнения")
    private Long employeeId;

    @NotNull(message = "Обязательно для заполнения")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate workDate;

    @NotNull(message = "Обязательно для заполнения")
    @PositiveOrZero(message = "Значение должно быть больше или ровно нулю")
    private Integer hourAtWork;

    @NotNull(message = "Обязательно для заполнения")
    private TypeOfAttendance typeOfAttendance;
}
