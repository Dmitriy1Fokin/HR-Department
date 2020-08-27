package ru.fds.hrdepartment;

import ru.fds.hrdepartment.domain.AttendanceSheet;
import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.Position;
import ru.fds.hrdepartment.domain.SickLeave;
import ru.fds.hrdepartment.domain.Vacation;
import ru.fds.hrdepartment.domain.helpertype.TypeOfAttendance;
import ru.fds.hrdepartment.domain.helpertype.TypeOfSickLeave;
import ru.fds.hrdepartment.domain.helpertype.TypeOfVacation;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static AttendanceSheet getAttendanceSheet(){
        return new AttendanceSheet(1L,
                new Employee(),
                LocalDate.now(),
                8,
                TypeOfAttendance.AT_WORK);
    }

    public static Department getDepartment(){
        return new Department(1L, "department name");
    }

    public static Employee getEmployee1(){
        return new Employee(1L,
                "firstName",
                "lastName",
                LocalDate.of(2020, Month.JANUARY, 1),
                null,
                new Department(),
                new Position());
    }

    public static Employee getEmployee2(){
        return new Employee(2L,
                "firstName2",
                "lastName2",
                LocalDate.of(2020, Month.APRIL, 11),
                null,
                new Department(),
                new Position());
    }

    public static List<Employee> getEmployees(){
        return Arrays.asList(getEmployee1(), getEmployee2());
    }

    public static Position getPosition(){
        return new Position(1L,"name");
    }

    public static SickLeave getSickLeave(){
        return new SickLeave(1L,
                new Employee(),
                LocalDate.of(2020, Month.JANUARY, 01),
                LocalDate.of(2020, Month.APRIL, 22),
                TypeOfSickLeave.BASIC);
    }

    public static Vacation getVacation(){
        return new Vacation(1L,
                new Employee(),
                LocalDate.of(2020, Month.APRIL, 11),
                LocalDate.of(2020, Month.APRIL, 15),
                TypeOfVacation.ADDITIONAL_VACATION);
    }
}
