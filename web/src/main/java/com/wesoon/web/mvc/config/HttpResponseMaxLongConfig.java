package com.wesoon.web.mvc.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class HttpResponseMaxLongConfig {

    /**
     * 解决前端js处理大数字丢失精度问题，将大于 [2的53次方]
     */
    @Bean("jackson2ObjectMapperBuilderCustomizer")
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        Jackson2ObjectMapperBuilderCustomizer customizer =
                jacksonObjectMapperBuilder ->
                        jacksonObjectMapperBuilder.serializerByType(Long.class, MaxLongToStringSerializer.instance)
                                .serializerByType(Long.TYPE, MaxLongToStringSerializer.instance);
        return customizer;
    }
}

class MaxLongToStringSerializer extends StdSerializer<Long> {

    public final static MaxLongToStringSerializer instance = new MaxLongToStringSerializer();

    public MaxLongToStringSerializer() {
        super(Long.class);
    }

    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value == 0) {
            gen.writeNumber(value);
            return;
        }
        long temp = value >> 53;
        if (temp > 0) {
            gen.writeString(value.toString());
        } else {
            gen.writeNumber(value);
        }
    }
}
