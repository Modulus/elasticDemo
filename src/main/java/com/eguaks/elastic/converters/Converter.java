package com.eguaks.elastic.converters;

import com.eguaks.elastic.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.net.www.content.text.Generic;

import java.io.IOException;

/**
 * Created by Modulus on 20.02.2016.
 */
public class Converter<T> {

    private ObjectMapper mapper;

    public Converter(ObjectMapper mapper){
        this.mapper = mapper;
    }

    public Converter(){
        this.mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
    }

    public String convertToJson(T user) throws JsonProcessingException {
        return mapper.writeValueAsString(user);
    }

    public T convertFromJson(String user, Class<T> clazz) throws IOException {
        return mapper.readValue(user, clazz);
    }
}
