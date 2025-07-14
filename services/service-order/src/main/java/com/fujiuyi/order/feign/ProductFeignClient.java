package com.fujiuyi.order.feign;

import com.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 通过注解的方式就可以实现远程调用
 */
@FeignClient(name = "service-product")
public interface ProductFeignClient {

    @GetMapping("/product/getProduct/{id}")
    Product getProduct(@PathVariable("id") Long id);


    @PostMapping("/product/create")
    Product createProduct(@RequestBody Product product);

}
