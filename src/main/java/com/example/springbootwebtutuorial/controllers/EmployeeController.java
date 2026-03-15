package com.example.springbootwebtutuorial.controllers;

import com.example.springbootwebtutuorial.dto.EmployeeDTO;
import com.example.springbootwebtutuorial.entities.EmployeeEntity;
import com.example.springbootwebtutuorial.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/{employeeId}")//http://localhost:8080/employees/12
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id){
            //return new EmployeeDTO(employeeId,"Jai","test@gmail.com", 27, LocalDate.of(2024,02,05),true);
        return employeeRepository.findById(id).orElse(null);
    }
    @GetMapping //http://localhost:8080/employees?age=21&sortBy=jai
    public List<EmployeeEntity>  getAllEmployee(@RequestParam(required = false) Integer age,
                                               @RequestParam(required = false) String sortBy){
        //return "Hi age " + age + " " + sortBy;
        return employeeRepository.findAll();
    }
    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeeRepository.save(inputEmployee);
    }
}
