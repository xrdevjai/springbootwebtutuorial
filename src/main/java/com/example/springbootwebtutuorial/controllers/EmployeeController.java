package com.example.springbootwebtutuorial.controllers;

import com.example.springbootwebtutuorial.dto.EmployeeDTO;
import com.example.springbootwebtutuorial.exception.ResourceNotFound;
import com.example.springbootwebtutuorial.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")//http://localhost:8080/employees/12
    public ResponseEntity<Optional<EmployeeDTO>> getEmployeeById(@PathVariable(name = "employeeId") Long id){
            //return new EmployeeDTO(employeeId,"Jai","test@gmail.com", 27, LocalDate.of(2024,02,05),true);
        Optional<EmployeeDTO> employeeDTO =  employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO))
                .orElseThrow(() -> new ResourceNotFound("Employee Not Found with id: "+id));
    }

    @GetMapping //http://localhost:8080/employees?age=21&sortBy=jai
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(@RequestParam(required = false) Integer age,
                                               @RequestParam(required = false) String sortBy){
        //return "Hi age " + age + " " + sortBy;
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }
    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> UpdateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.UpdateEmployeeById(employeeId,employeeDTO));
    }
    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId,updates);
        if(employeeDTO == null) return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}
