package com.fujiuyi.order.interceptor;

import feign.InvocationContext;
import feign.ResponseInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
public class ProductResponceInterceptor implements ResponseInterceptor {

    @Override
    public Object intercept(InvocationContext invocationContext, Chain chain) throws Exception {
        log.info("ProductResponceInterceptor: {}", invocationContext);
        log.info("ProductResponceInterceptor: {}", chain);
        return null;
    }
}
