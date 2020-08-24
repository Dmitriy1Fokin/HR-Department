package ru.fds.hrdepartment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ru.fds.hrdepartment.domain.helpertype.TypeOfAttendance;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "vacation_sick")
public class VacationSick {

    @Id
    @SequenceGenerator(name = "vacation_sick_id_seq", sequenceName = "vacation_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vacation_sick_id_seq")
    private Long id;

    @JoinColumn(name = "employee_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Обязательно для заполнения")
    private Employee employee;

    @Column(name = "date_start")
    @NotNull(message = "Обязательно для заполнения")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateStart;

    @Column(name = "date_end")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnd;

    @Column(name ="type_of_vacation_sick")
    @NotNull(message = "Обязательно для заполнения")
    @Convert(converter = TypeOfAttendance.Converter.class)
    private TypeOfAttendance typeOfAttendance;
}
