package com.idltd.hydramob.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;


public class StaticUtils {
    //Преобразует Object в Json
    public static String ConvertObjectToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = new String();
        try {
            json = ow.writeValueAsString(obj);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
    //Преобразует BigDecimal в Long (идиотизм конечно но блин ненашел пока)
    public static Long convertBigDecimalToLong(BigDecimal b) {
        if ( b!=null ) return new Long(b.longValue());
        else return null;
    }


    //Возвращает имя пользователя сессии
    public static String GetUserName() {
        String result=null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        result = auth.getName();
        return result;
    }

    //Возвращает трассировку исключения как строку
    public static String ConvertTraceExceptionToText(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

}
