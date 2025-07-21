package com.fujiuyi.order.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.common.AjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

@Component
public class CustomExceptionHandler implements BlockExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       String resourceName, BlockException e) throws Exception {

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        AjaxResult ajaxResult = AjaxResult.error("资源:" + resourceName + "访问失败:" + e.getClass());
        String result  =objectMapper.writeValueAsString(ajaxResult);
        printWriter.write(result);

        printWriter.flush();
        printWriter.close();
    }
}
