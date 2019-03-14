package com.lunatech.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunatech.kafka.common.java.Introduction;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;


public class IntroductionSerializer implements Serializer<Introduction> {
    private ObjectMapper mapper;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        mapper = new ObjectMapper();
    }

    @Override
    public byte[] serialize(String topic, Introduction data) {
        try {
            return mapper.writeValueAsBytes(data);
        } catch (JsonProcessingException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public void close() {}
}
