package com.etiya.rentACar.core.exceptions.types;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message); //inheritance aldığımız class ın constrocturına gider burada RuntimeException
    }
}
