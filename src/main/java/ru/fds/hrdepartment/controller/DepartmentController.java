package ru.fds.hrdepartment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fds.hrdepartment.service.DepartmentService;

@RestController
@RequestMapping("/dep")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/total_hour")
    public Integer getWorkHoursInDepartment(@RequestParam("departmentId") Long departmentId){
        return departmentService.getWorkHoursInDepartment(departmentId);
    }
}
