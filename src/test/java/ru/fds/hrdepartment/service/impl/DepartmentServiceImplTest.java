package ru.fds.hrdepartment.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.fds.hrdepartment.TestUtils;
import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.repository.AttendanceSheetRepository;
import ru.fds.hrdepartment.repository.DepartmentRepository;
import ru.fds.hrdepartment.service.DepartmentService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;
    @MockBean
    private AttendanceSheetRepository attendanceSheetRepository;


    @BeforeEach
    void setUp() {
        Department department = TestUtils.getDepartment();

        Mockito.when(departmentRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(department));
        Mockito.when(attendanceSheetRepository.getWorkHoursInDepartment(Mockito.any(Department.class)))
                .thenReturn(1);
        Mockito.when(departmentRepository.save(Mockito.any(Department.class)))
                .thenReturn(department);
    }

    @Test
    void getDepartment() {
        Optional<Department> department = departmentService.getDepartment(1L);

        assertEquals(TestUtils.getAttendanceSheet().getId(), department.get().getId());
    }

    @Test
    void getWorkHoursInDepartment() {
        int testCount = departmentService.getWorkHoursInDepartment(1L);

        assertEquals(1, testCount);
    }

    @Test
    void insertDepartment() {
        Department department = departmentService.insertDepartment(TestUtils.getDepartment());

        assertEquals(TestUtils.getDepartment().getId(), department.getId());
    }

    @Test
    void updateDepartment() {
        Department department = departmentService.updateDepartment(TestUtils.getDepartment());

        assertEquals(TestUtils.getDepartment().getId(), department.getId());
    }
}