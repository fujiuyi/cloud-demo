package com.fujiuyi.product.service.impl;

import com.fujiuyi.product.service.ProductService;
import com.product.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService {


    @Override
    public Product getProductById(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setNum(10);
        product.setPrice(new BigDecimal(10));
        product.setProductName("productName");

        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return product;
    }

    @Override
    public Product createProduct(Product product) {
        return product.clone();
    };
}
