package com.example.csmd3checkin.util;

import javax.servlet.http.HttpServletRequest;

public class AppUtil {
    public static Object getParameterWithDefaultValue(HttpServletRequest request, String name, Object valueDefault){
        String value = request.getParameter(name);
        if(value == null)return valueDefault;
        return value;
    }
}
