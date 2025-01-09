package com.bipartite.v1.Controller;

import java.lang.reflect.Method;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler{
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
        System.err.println("Exception in async method: " + throwable.getMessage());
    }
}
