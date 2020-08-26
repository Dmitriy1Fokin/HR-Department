package ru.fds.hrdepartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.Position;
import ru.fds.hrdepartment.domain.helpertype.TypeOfAttendance;

import java.time.LocalDate;
import java.util.Collection;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select count(asheet) from AttendanceSheet asheet where asheet.employee.id = :employeeId")
    Integer getWorkDaysByEmployee(@Param("employeeId") Long employeeId);

    @Query("select asheet.employee from AttendanceSheet asheet where asheet.typeOfAttendance = :typeOfAttendance and asheet.workDate = :date")
    Collection<Employee> getEmployeeByTypeOfAttendance(@Param("date") LocalDate date,
                                                       @Param("typeOfAttendance") TypeOfAttendance typeOfAttendance);

    Collection<Employee> findAllByDepartmentAndPosition(Department department, Position position);

    @Query("select count(sl) " +
            "from SickLeave sl " +
            "where sl.employee in :employees " +
            "and (sl.dateEnd is null or sl.dateEnd >= :dateEnd)")
    Integer countOfSickEmployees(@Param("employees") Collection<Employee> employees,
                                 @Param("dateEnd") LocalDate dateEnd);
    @Query("select count(v) " +
            "from Vacation v " +
            "where v.employee in :employees " +
            "and v.dateStart <= :date " +
            "and (v.dateEnd >= :date or v.dateEnd is null )")
    Integer countOfEmployeesInVacation(@Param("employees") Collection<Employee> employees,
                                       @Param("date") LocalDate date);

}
