package com.fujiuyi.product.service.impl;

import com.fujiuyi.product.service.ProductService;
import com.product.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {


    @Override
    public Product getProductById(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setNum(10);
        product.setPrice(new BigDecimal(10));
        product.setProductName("productName");
        return product;
    }
}
