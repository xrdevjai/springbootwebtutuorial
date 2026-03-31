package com.example.springbootwebtutuorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//POJO CLASS ---> Plane old java object --> used to define entity inside the code. --> can't define any custom logic.
public class EmployeeDTO {
    private Long id;
    //@NotNull(message = "Required field in Employee: name")
    //@NotEmpty(message = "Employee: name should not be empty")
    @NotBlank(message = "Employee: name should not be blank")
    @Size(min = 3, max = 10, message = "Name should be between the range [3:10]")
    private String name;

    @Email(message = "Enter a valid email address")
    private String email;

    @Max(value = 80, message = "Age can't greater than 80")
    @Min(value = 18, message = "Age can't smaller than 18")
    private Integer age;

    @NotBlank(message = "Role of the employee cannot be blank")
    @Pattern(regexp = "^(ADMIN||USER)$", message = "Role of Employee can either be USER or ADMIN")
    private String role;

    @NotNull(message = "Employee Salary should not be null")
    @Positive(message = "Salary must be positive")
    private Integer Salary;

    @PastOrPresent(message = "Data of joining date should be present or past")
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    private Boolean isActive;

}
