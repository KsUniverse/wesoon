package com.wesoon.web.mvc.config;

import com.alibaba.fastjson.JSONObject;
import com.wesoon.web.mvc.RestResult;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Component
public class LocalStringHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    public LocalStringHttpMessageConverter() {
        super(MediaType.APPLICATION_JSON, new MediaType("application", "*+json"), MediaType.TEXT_PLAIN);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return String.class == clazz || RestResult.class == clazz;
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        if(clazz == RestResult.class) {
            return JSONObject.parseObject(StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8), RestResult.class);
        } else if (clazz == String.class) {
            return StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8);
        }
        throw new RuntimeException("http message converter exception");
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if(o instanceof RestResult) {
            StreamUtils.copy(JSONObject.toJSONString(o), StandardCharsets.UTF_8, outputMessage.getBody());
        } else if (o instanceof String) {
            StreamUtils.copy(o.toString(), StandardCharsets.UTF_8, outputMessage.getBody());
        }
    }
}
