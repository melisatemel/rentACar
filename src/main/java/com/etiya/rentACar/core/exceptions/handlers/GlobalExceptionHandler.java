package com.etiya.rentACar.core.exceptions.handlers;

import com.etiya.rentACar.core.exceptions.problemdetails.BusinessProblemDetails;
import com.etiya.rentACar.core.exceptions.problemdetails.InternalServerProblemDetails;
import com.etiya.rentACar.core.exceptions.problemdetails.ValidationProblemDetails;
import com.etiya.rentACar.core.exceptions.types.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler({BusinessException.class})    //instance oluştur
    @ResponseStatus(code= HttpStatus.BAD_REQUEST)

    public BusinessProblemDetails handleBusinessException(BusinessException businessException){
        BusinessProblemDetails businessProblemDetails = new BusinessProblemDetails();
        businessProblemDetails.setDetail(businessException.getMessage());
        return businessProblemDetails;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception){

        List<Map<String,String>> errorList =
        exception.getBindingResult().getFieldErrors().stream().map(      //Reflection yöntemi
                fieldError -> {
                    Map<String,String> validationError = new HashMap<>();
                    validationError.put(fieldError.getField(),fieldError.getDefaultMessage());  //eşleştirme gerektiği için map kullanılır.
                    return validationError; //map return edilmez
                }).collect(Collectors.toList());


        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
        validationProblemDetails.setErrors(errorList);
        return validationProblemDetails;    //Dönüş tipi liste olamaz çünkü map tutuyor
    }

    @ExceptionHandler({Exception.class})    //Genel hataları aldık
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public InternalServerProblemDetails handleInternalServerException(Exception exception){
        InternalServerProblemDetails internalServerProblemDetails = new InternalServerProblemDetails();
        internalServerProblemDetails.setDetail(exception.getMessage()); //tekrar bakılacak
        return internalServerProblemDetails;
    }
}
