package com.etiya.rentACar.core.exceptions.problemdetails;

import org.springframework.http.HttpStatus;

public class InternalServerProblemDetails extends ProblemDetails{
    public InternalServerProblemDetails(){
        setTitle("Internal Server Rule Violation!");
        setType("http://etiya.com/exeptionsinternalserver");
        setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());

    }
}
