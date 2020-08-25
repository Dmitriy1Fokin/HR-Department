package ru.fds.hrdepartment.service.impl;

import org.springframework.stereotype.Service;
import ru.fds.hrdepartment.domain.Department;
import ru.fds.hrdepartment.repository.DepartmentRepository;
import ru.fds.hrdepartment.service.DepartmentService;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Optional<Department> getDepartment(Long departmentId) {
        return departmentRepository.findById(departmentId);
    }

    @Override
    public Integer getWorkHoursByDepartment(Long departmentId) {
        return null;
    }
}
