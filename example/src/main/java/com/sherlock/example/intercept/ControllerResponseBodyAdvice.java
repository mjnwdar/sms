package com.sherlock.example.intercept;

import com.sherlock.rest.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Sherlock
 */
@Slf4j
@ControllerAdvice
public class ControllerResponseBodyAdvice<T> implements ResponseBodyAdvice<T> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return false;
    }

    @Override
    public T beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest,
        ServerHttpResponse serverHttpResponse) {
        return (T) ResultEntity.success("OK", o);
    }
}
