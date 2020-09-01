package ru.fds.hrdepartment.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.fds.hrdepartment.TestUtils;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.dao.AttendanceSheetDao;
import ru.fds.hrdepartment.dao.DepartmentDao;
import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.service.DepartmentService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentDao departmentDao;
    @MockBean
    private AttendanceSheetDao attendanceSheetDao;


    @BeforeEach
    void setUp() {
        Department department = TestUtils.getDepartment();

        Mockito.when(departmentDao.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(department));
        Mockito.when(attendanceSheetDao.getWorkHoursInDepartment(Mockito.any(Department.class)))
                .thenReturn(1);
        Mockito.when(departmentDao.save(Mockito.any(Department.class)))
                .thenReturn(department);
        Mockito.when(departmentDao.update(Mockito.any(Department.class)))
                .thenReturn(department);
    }

    @Test
    void getDepartment() {
        Optional<Department> department = departmentService.getDepartment(1L);

        department.ifPresentOrElse(department1 -> assertEquals(TestUtils.getAttendanceSheet().getId(), department1.getId()),
                () -> {throw new NotFoundException("Department not found");});
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