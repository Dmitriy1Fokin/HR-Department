package ru.fds.hrdepartment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HrdepartmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrdepartmentApplication.class, args);
    }

}
