package com.javatechie.report;

import com.javatechie.report.entity.Employee;
import com.javatechie.report.repository.EmployeeRepository;
import com.javatechie.report.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@SpringBootApplication
@RestController
public class SpringBootJasperReportApplication {

    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private ReportService service;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {

        return repository.findAll();
    }

    @PostMapping("/employees")
    public Employee saveEmployees(@RequestBody Employee employee) {

        return repository.save(employee);
    }


    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return service.exportReport(format);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJasperReportApplication.class, args);
    }

}
