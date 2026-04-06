package com.example.springbootwebtutuorial.advice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Data
@Builder
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> subErrors;
}
