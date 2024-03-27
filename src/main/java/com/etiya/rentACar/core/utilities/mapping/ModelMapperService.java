package com.etiya.rentACar.core.utilities.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forResponse();  //ModelMapper kütüphaneden geliyor
    ModelMapper forRequest();
}
