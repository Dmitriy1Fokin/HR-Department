package ru.fds.hrdepartment.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class EmployeeDto implements Serializable {

    private Long id;

    @NotNull(message = "Обязательно для заполнения")
    private String firstName;

    @NotNull(message = "Обязательно для заполнения")
    private String lastName;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = "Обязательно для заполнения")
    private LocalDate dateIn;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOut;

    @NotNull(message = "Обязательно для заполнения")
    private Long departmentId;

    @NotNull(message = "Обязательно для заполнения")
    private Long positionId;
}
