package com.zyh.webstudy.utils;


import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 输出流
 * */
public class ResponseJsonUtil {
    public static void out(HttpServletResponse response, ResultUtil result) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        try {
            mapper.writeValue(response.getWriter(), result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
