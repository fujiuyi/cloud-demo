package com.fujiuyi.product.controller;

import lombok.extern.slf4j.Slf4j;
import com.fujiuyi.product.service.ProductService;
import com.product.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductRestController {

    ProductService productService;
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getProduct/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        log.info("server-product 接收到请求：productId：{}", id);
        return productService.getProductById(id);
    }
}
