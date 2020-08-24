package ru.fds.hrdepartment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "attendance_sheet")
public class AttendanceSheet {

    @Id
    @SequenceGenerator(name = "attendance_id_seq", sequenceName = "attendance_sheet_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_id_seq")
    private Long id;

    @JoinColumn(name = "employee_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Обязательно для заполнения")
    private Employee employee;

    @Column(name = "date")
    @NotNull(message = "Обязательно для заполнения")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate workDate;

    @Column(name = "hour_at_work")
    @NotNull(message = "Обязательно для заполнения")
    @PositiveOrZero(message = "Значение должно быть больше или ровно нулю")
    private Integer hourAtWork;

    @Column(name ="type_of_attendance")
    @NotNull(message = "Обязательно для заполнения")
    @Convert(converter = TypeOfAttendance.Converter.class)
    private TypeOfAttendance typeOfAttendance;

    @Override
    public String toString() {
        return "AttendanceSheet{" +
                "id=" + id +
                ", workDate=" + workDate +
                ", hourAtWork=" + hourAtWork +
                ", typeOfAttendance=" + typeOfAttendance +
                '}';
    }
}
