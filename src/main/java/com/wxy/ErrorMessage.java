package com.wxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Description
 * @Author WangXingYu
 * @Data 2018/11/14
 * @Version 1.0
 */

@Component
@Order(0)
public class ErrorMessage implements ApplicationRunner {
    @Value("${errorPath}")
    private String errorPath;

    private static Properties properties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        InputStream in = ErrorMessage.class.getResourceAsStream(errorPath);
        properties = new Properties();
        properties.load(in);
    }

    public static String getErrorMessage(String code) {
        if(properties == null)
            throw new RuntimeException("error message is not initialized, please not use this on start up");
        return properties.getProperty(code);
    }
}
