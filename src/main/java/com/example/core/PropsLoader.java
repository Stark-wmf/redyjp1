package com.example.core;

import com.example.been.ResponseEntity;
import com.example.been.ShiinaContext;
import com.example.util.CastUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.Properties;

public class PropsLoader {
    private static PropsLoader singleton;

    private Properties properties;

    private static final String PROPS_PATH = "/WEB-INF/application.properties";

    public static PropsLoader getInstance() {
        if (singleton != null) {
            return singleton;
        }
        throw new RuntimeException("PropsLoader null");
    }

    /**
     * 初始化配置
     * @param context servlet上下文
     */
    public static void init(ServletContext context){
        if(singleton==null){
            synchronized (PropsLoader.class){
                if(singleton==null){
                    singleton=new PropsLoader(context);
                }
            }
        }
    }

    /**
     * 不允许使用任何方式调用无参构造器
     * @throws IllegalAccessException
     */
    private PropsLoader() throws IllegalAccessException {
        throw new IllegalAccessException();
    }


    private PropsLoader(ServletContext context) {
        InputStream in = context.getResourceAsStream(PROPS_PATH);
        if (in != null) {
            properties = new Properties();
            try {
                properties.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("application.properties Not Found");
        }
    }

    public String getString(String key) {
        String res;
        if (key != null) {
            res = properties.getProperty(key);
            if (res != null) {
                return res;
            }
            return "";
        }
        throw new RuntimeException("properties " + key + " not found.");
    }

    public int getInt(String key) {
        return CastUtil.castInt(getString(key), 0);
    }

    public long getLong(String key) {
        return CastUtil.castLong(getString(key), 0L);
    }

}
