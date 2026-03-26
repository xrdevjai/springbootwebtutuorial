package com.example.springbootwebtutuorial.controllers;

import com.example.springbootwebtutuorial.dto.EmployeeDTO;
import com.example.springbootwebtutuorial.entities.EmployeeEntity;
import com.example.springbootwebtutuorial.repositories.EmployeeRepository;
import com.example.springbootwebtutuorial.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")//http://localhost:8080/employees/12
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){
            //return new EmployeeDTO(employeeId,"Jai","test@gmail.com", 27, LocalDate.of(2024,02,05),true);
        return employeeService.getEmployeeById(id);
    }
    @GetMapping //http://localhost:8080/employees?age=21&sortBy=jai
    public List<EmployeeDTO> getAllEmployee(@RequestParam(required = false) Integer age,
                                               @RequestParam(required = false) String sortBy){
        //return "Hi age " + age + " " + sortBy;
        return employeeService.getAllEmployee(age,sortBy);
    }
    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }
}
