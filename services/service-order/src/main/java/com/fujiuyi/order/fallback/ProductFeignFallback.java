package com.fujiuyi.order.fallback;

import com.fujiuyi.order.feign.ProductFeignClient;
import com.product.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFeignFallback implements ProductFeignClient {

    @Override
    public Product getProduct(Long id) {
        Product product = new Product();
        product.setProductName("服务降级");
        product.setId(id);
        product.setNum(0);
        product.setPrice(new BigDecimal(0));
        return product;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
