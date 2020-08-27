package ru.fds.hrdepartment.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.fds.hrdepartment.TestUtils;
import ru.fds.hrdepartment.common.exception.ConditionFailException;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.domain.Vacation;
import ru.fds.hrdepartment.repository.EmployeeRepository;
import ru.fds.hrdepartment.repository.VacationRepository;
import ru.fds.hrdepartment.service.VacationService;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VacationServiceImplTest {

    @Autowired
    private VacationService vacationService;

    @MockBean
    private VacationRepository vacationRepository;
    @MockBean
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        Vacation vacation = TestUtils.getVacation();

        Mockito.when(vacationRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(vacation));
        Mockito.when(vacationRepository.save(Mockito.any(Vacation.class)))
                .thenReturn(vacation);
        Mockito.when(vacationRepository.findFirstByEmployee(Mockito.any(Employee.class), Mockito.any()))
                .thenReturn(Optional.of(vacation));
        Mockito.when(employeeRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(TestUtils.getEmployee1()));
    }

    @Test
    void getVacation() {
        Optional<Vacation> vacation = vacationService.getVacation(Mockito.anyLong());

        assertEquals(TestUtils.getVacation().getId(), vacation.get().getId());
    }

    @Test
    void insertVacation() {
        Mockito.when(employeeRepository.findAllByDepartmentAndPosition(Mockito.any(), Mockito.any()))
                .thenReturn(TestUtils.getEmployees());
        Mockito.when(employeeRepository.countOfSickEmployees(Mockito.anyCollection(), Mockito.any()))
                .thenReturn(0);

        Vacation vacation = vacationService.insertVacation(TestUtils.getVacation());

        assertEquals(TestUtils.getVacation().getId(), vacation.getId());
    }

    @Test
    void insertVacation_ConditionFailException() {
        Mockito.when(employeeRepository.findAllByDepartmentAndPosition(Mockito.any(), Mockito.any()))
                .thenReturn(Collections.singletonList(TestUtils.getEmployees().get(0)));

        Exception exception = assertThrows(ConditionFailException.class,
                () -> vacationService.insertVacation(TestUtils.getVacation()));

        assertNotNull(exception);
    }

    @Test
    void updateVacation() {
        Vacation vacation = vacationService.updateVacation(TestUtils.getVacation());

        assertEquals(TestUtils.getVacation().getId(), vacation.getId());
    }

    @Test
    void getLastVacationByEmployee() {
        Optional<Vacation> vacation = vacationService.getLastVacationByEmployee(Mockito.anyLong());

        assertEquals(TestUtils.getVacation().getId(), vacation.get().getId());
    }
}