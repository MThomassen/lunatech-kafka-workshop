package com.lunatech.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunatech.kafka.common.java.Introduction;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class IntroductionDeserializer implements Deserializer<Introduction> {
    private ObjectMapper mapper;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        mapper = new ObjectMapper();
    }

    @Override
    public Introduction deserialize(String topic, byte[] data) {
        try {
            return mapper.readValue(data, Introduction.class);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public void close() {}
}
