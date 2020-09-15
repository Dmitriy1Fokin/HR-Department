package ru.fds.hrdepartment.rabbitlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.fds.hrdepartment.common.converter.dto.impl.EmployeeDtoConverter;
import ru.fds.hrdepartment.domain.Employee;
import ru.fds.hrdepartment.dto.EmployeeDto;
import ru.fds.hrdepartment.service.EmployeeService;


@Slf4j
@EnableRabbit
@Component
public class EmployeeListener {

    private final EmployeeService employeeService;
    private final EmployeeDtoConverter employeeDtoConverter;

    public EmployeeListener(EmployeeService employeeService,
                            EmployeeDtoConverter employeeDtoConverter) {
        this.employeeService = employeeService;
        this.employeeDtoConverter = employeeDtoConverter;
    }

    @RabbitListener(queues = "newEmployeeQueue")
    public Long executeTest(EmployeeDto employeeDto){
        log.info("receive message: {}", employeeDto);

        Employee employee = employeeDtoConverter.toEntity(employeeDto);
        employee = employeeService.insertEmployee(employee);

        log.info("insert employee: {}", employee);
        return employee.getId();
    }
}
