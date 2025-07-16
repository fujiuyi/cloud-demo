package com.fujiuyi.product.controller;

import lombok.extern.slf4j.Slf4j;
import com.fujiuyi.product.service.ProductService;
import com.product.Product;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductRestController {

    ProductService productService;
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getProduct/{id}")
    public Product getProduct(@PathVariable("id") Long id,
                              @RequestHeader("X-Request-By") String header) {
        log.info("server-product 接收到请求头：header：{}", header);
        log.info("server-product 接收到请求：productId：{}", id);
        return productService.getProductById(id);
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
}
