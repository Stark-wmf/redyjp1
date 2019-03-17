package com.example.core;

import com.example.been.ResponseEntity;
import com.example.been.ShiinaContext;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

public class Handle {
    /**
     * 全局统一的Json处理库
     */
    private static final Gson GSON = new Gson();

    /**
     * 用来处理请求的方法
     */
    private Method method;

    /**
     * 该方法所属的controller
     */
    private Object controller;

    Handle(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }
    String invoke(ShiinaContext context) {
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();

        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String key = params.nextElement();
            String value = request.getParameter(key);
            context.putParam(key, value);
        }

        ResponseEntity res = null;
        try {
            res = (ResponseEntity) method.invoke(controller, context);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        if (res != null) {
            return GSON.toJson(res);
        }
        return null;
    }
}
