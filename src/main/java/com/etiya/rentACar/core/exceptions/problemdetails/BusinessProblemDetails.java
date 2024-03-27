package com.etiya.rentACar.core.exceptions.problemdetails;

import org.springframework.http.HttpStatus;

public class BusinessProblemDetails extends ProblemDetails {
    public BusinessProblemDetails(){   //runtime anında çalışması için constructor yaptık
        setTitle("Business Rule Violation!");
        setType("http://etiya.com/exceptionsbusiness");
        setStatus(HttpStatus.BAD_REQUEST.toString());   //hata fırlatırsan bad request fırlat 500 fırlatma
    }
}
