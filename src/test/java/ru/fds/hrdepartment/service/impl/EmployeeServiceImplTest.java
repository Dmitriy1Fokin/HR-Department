package ru.fds.hrdepartment.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.fds.hrdepartment.TestUtils;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.dao.EmployeeDao;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.service.EmployeeService;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeDao employeeDao;

    @BeforeEach
    void setUp() {
        Employee employee = TestUtils.getEmployee1();

        Mockito.when(employeeDao.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(employee));
        Mockito.when(employeeDao.getWorkDaysByEmployee(Mockito.anyLong()))
                .thenReturn(1);
        Mockito.when(employeeDao.save(Mockito.any(Employee.class)))
                .thenReturn(employee);
        Mockito.when(employeeDao.update(Mockito.any(Employee.class)))
                .thenReturn(employee);
        Mockito.when(employeeDao.getEmployeeByTypeOfAttendance(Mockito.any(), Mockito.any()))
                .thenReturn(TestUtils.getEmployees());
    }


    @Test
    void getEmployee() {
        Optional<Employee> employee = employeeService.getEmployee(Mockito.anyLong());

        employee.ifPresentOrElse(employee1 -> assertEquals(TestUtils.getEmployee1().getId(),employee1.getId()),
                () -> {throw new NotFoundException("Employee not found");});
    }

    @Test
    void getWorkDaysByEmployee() {
        int testCount = employeeService.getWorkDaysByEmployee(Mockito.anyLong());

        assertEquals(1L, testCount);
    }

    @Test
    void insertEmployee() {
        Employee employee = employeeService.insertEmployee(TestUtils.getEmployee1());

        assertEquals(TestUtils.getEmployee1().getId(), employee.getId());
    }

    @Test
    void updateEmployee() {
        Employee employee = employeeService.updateEmployee(TestUtils.getEmployee1());

        assertEquals(TestUtils.getEmployee1().getId(), employee.getId());
    }

    @Test
    void getEmployeeByTypeOfAttendance() {
        Collection<Employee> employees = employeeService.getEmployeeByTypeOfAttendance(Mockito.any(), Mockito.any());

        assertEquals(TestUtils.getEmployees().size(), employees.size());
    }
}