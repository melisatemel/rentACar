package com.etiya.rentACar.core.exceptions.problemdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;
@EqualsAndHashCode(callSuper = true)
@Data
public class ValidationProblemDetails extends ProblemDetails{

    public ValidationProblemDetails(){
        setTitle("Validation Rule Violation");
        setDetail("Validation Problem");
        setType("http://etiya.com/exceptions/validation");
        setStatus(HttpStatus.BAD_REQUEST.toString());
    }

    private List<Map<String,String>> errors;      //key olarakta string value olarakta string
}
