package ru.fds.hrdepartment.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.fds.hrdepartment.TestUtils;
import ru.fds.hrdepartment.common.exception.ConditionFailException;
import ru.fds.hrdepartment.common.exception.NotFoundException;
import ru.fds.hrdepartment.dao.EmployeeDao;
import ru.fds.hrdepartment.dao.SickLeaveDao;
import ru.fds.hrdepartment.domain.SickLeave;
import ru.fds.hrdepartment.service.SickLeaveService;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SickLeaveServiceImplTest {

    @Autowired
    private SickLeaveService sickLeaveService;

    @MockBean
    SickLeaveDao sickLeaveDao;
    @MockBean
    EmployeeDao employeeDao;

    @BeforeEach
    void setUp() {
        SickLeave sickLeave = TestUtils.getSickLeave();

        Mockito.when(sickLeaveDao.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(sickLeave));
        Mockito.when(sickLeaveDao.save(Mockito.any(SickLeave.class)))
                .thenReturn(sickLeave);
        Mockito.when(sickLeaveDao.update(Mockito.any(SickLeave.class)))
                .thenReturn(sickLeave);
    }

    @Test
    void getSickLeave() {
        Optional<SickLeave> sickLeave = sickLeaveService.getSickLeave(Mockito.anyLong());

        sickLeave.ifPresentOrElse(sickLeave1 -> assertEquals(TestUtils.getSickLeave().getId(), sickLeave1.getId()),
                () -> {throw new NotFoundException("SickLeave not found");});
    }

    @Test
    void insertSickLeave() {
        Mockito.when(employeeDao.findAllByDepartmentAndPosition(Mockito.any(), Mockito.any()))
                .thenReturn(TestUtils.getEmployees());
        Mockito.when(employeeDao.countOfSickEmployees(Mockito.anyCollection(), Mockito.any()))
                .thenReturn(0);

        SickLeave sickLeave = sickLeaveService.insertSickLeave(TestUtils.getSickLeave());

        assertEquals(TestUtils.getSickLeave().getId(), sickLeave.getId());
    }

    @Test()
    void insertSickLeave_ConditionFailException() {
        Mockito.when(employeeDao.findAllByDepartmentAndPosition(Mockito.any(), Mockito.any()))
                .thenReturn(Collections.singletonList(TestUtils.getEmployees().get(0)));

        Exception exception = assertThrows(ConditionFailException.class,
                () -> sickLeaveService.insertSickLeave(TestUtils.getSickLeave()));

        assertNotNull(exception);
    }

    @Test
    void updateSickLeave() {
        SickLeave sickLeave = sickLeaveService.updateSickLeave(TestUtils.getSickLeave());

        assertEquals(TestUtils.getSickLeave().getId(), sickLeave.getId());
    }
}