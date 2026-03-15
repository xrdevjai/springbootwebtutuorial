package com.example.springbootwebtutuorial.controllers;

import com.example.springbootwebtutuorial.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    @GetMapping("/{employeeId}")//http://localhost:8080/employees/12
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
            return new EmployeeDTO(employeeId,"Jai","test@gmail.com", 27, LocalDate.of(2024,02,05),true);
    }
    @GetMapping //http://localhost:8080/employees?age=21&sortBy=jai
    public String getAllEmployee(@RequestParam(required = false) Integer age,
                                 @RequestParam(required = false) String sortBy){
        return "Hi age " + age + " " + sortBy;
    }
    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        inputEmployee.setId(100L);
        return inputEmployee;
    }
}
