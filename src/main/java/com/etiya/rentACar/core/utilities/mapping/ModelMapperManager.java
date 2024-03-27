package com.etiya.rentACar.core.utilities.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperManager implements ModelMapperService{
    private ModelMapper modelMapper;

    public ModelMapperManager(ModelMapper modelMapper){     //injection     @BEAN ile bağlanıyor
        this.modelMapper = modelMapper;
    }
    @Override
    public ModelMapper forResponse() {      //kaynak veritabanı Frontent;  hepsi maplenmediği için loose
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
        return this.modelMapper;
    }

    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true) //Get Configurationları çağır. .setAmbiguityIgnored =Belirsizlik varsa gözardı et
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return this.modelMapper;
    }
}
