package com.etiya.rentACar.core.exceptions.types;

public class BusinessException extends RuntimeException{
    public BusinessException(String messege){
        super(); //inheritance aldığımız class ın constrocturına gider burada RuntimeException
    }
}
