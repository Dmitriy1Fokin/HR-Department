package ru.fds.hrdepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.repository.AttendanceSheetRepository;
import ru.fds.hrdepartment.repository.DepartmentRepository;
import ru.fds.hrdepartment.service.DepartmentService;

import java.util.Optional;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final AttendanceSheetRepository attendanceSheetRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                                 AttendanceSheetRepository attendanceSheetRepository) {
        this.departmentRepository = departmentRepository;
        this.attendanceSheetRepository = attendanceSheetRepository;
    }

    @Override
    public Optional<Department> getDepartment(Long departmentId) {
        log.debug("getDepartment. departmentId = {}", departmentId);

        return departmentRepository.findById(departmentId);
    }

    @Override
    public Integer getWorkHoursInDepartment(Long departmentId) {
        log.debug("getWorkHoursInDepartment. departmentId = {}", departmentId);

        Optional<Department> department = departmentRepository.findById(departmentId);
        if(department.isEmpty()){
            log.debug("getWorkHoursInDepartment. count = 0");
            return 0;
        }

        Integer count = attendanceSheetRepository.getWorkHoursInDepartment(department.get());
        log.debug("getWorkHoursInDepartment. count = {}", count);
        return count;
    }

    @Override
    @Transactional
    public Department insertDepartment(Department department) {
        department = departmentRepository.save(department);
        log.info("insert new department. department: {}", department);
        return department;
    }

    @Override
    @Transactional
    public Department updateDepartment(Department department) {
        department = departmentRepository.save(department);
        log.info("update department. department: {}", department);
        return department;
    }
}
