package ru.fds.hrdepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fds.hrdepartment.dao.AttendanceSheetDao;
import ru.fds.hrdepartment.dao.DepartmentDao;
import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.service.DepartmentService;

import java.util.Optional;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao;
    private final AttendanceSheetDao attendanceSheetDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao,
                                 AttendanceSheetDao attendanceSheetDao) {
        this.departmentDao = departmentDao;
        this.attendanceSheetDao = attendanceSheetDao;
    }

    @Override
    public Optional<Department> getDepartment(Long departmentId) {
        log.debug("getDepartment. departmentId = {}", departmentId);

        return departmentDao.findById(departmentId);
    }

    @Override
    public Integer getWorkHoursInDepartment(Long departmentId) {
        int count = departmentDao.findById(departmentId)
                .map(attendanceSheetDao::getWorkHoursInDepartment)
                .orElse(0);

        log.debug("getWorkHoursInDepartment. count = {}", count);

        return count;
    }

    @Override
    @Transactional
    public Department insertDepartment(Department department) {
        department = departmentDao.save(department);
        log.info("insert new department. department: {}", department);
        return department;
    }

    @Override
    @Transactional
    public Department updateDepartment(Department department) {
        department = departmentDao.update(department);
        log.info("update department. department: {}", department);
        return department;
    }
}
