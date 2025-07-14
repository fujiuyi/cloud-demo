package com.fujiuyi.order.service.impl;

import com.fujiuyi.order.feign.ProductFeignClient;
import com.fujiuyi.order.service.OrderService;
import com.order.Order;
import com.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final DiscoveryClient discoveryClient;

    private final RestTemplate restTemplate;

    private final LoadBalancerClient loadBalancerClient;

    private final ProductFeignClient productFeignClient;

    public OrderServiceImpl(DiscoveryClient discoveryClient,
                            RestTemplate restTemplate,
                            LoadBalancerClient loadBalancerClient,
                            ProductFeignClient productFeignClient) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
        this.loadBalancerClient = loadBalancerClient;
        this.productFeignClient = productFeignClient;
    }

    @Override
    public Order createOrder(Long product, Long userId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setId(10L);
        order.setNickName("fujiuyi");
        order.setAddress("beijing");

        Product product1 = getProductByLoadBalanceAnnotation(product);
        order.setTotalAmount(new BigDecimal(product1.getNum()).multiply(product1.getPrice()));
        order.setProducts(Collections.singletonList(product1));
        return order;
    }

    @Override
    public Order createProduct() {
        Product product = new Product();
        product.setId(new Random().nextLong());
        product.setNum(11);
        product.setPrice(new BigDecimal(11));
        product.setProductName("11");

        Order order = new Order();
        order.setProducts(Collections.singletonList(createProductByFeignClient(product)));
        return order;
    }

    /**
     * 通过最原始的方式自行拼装ip地址和端口进行跨服务的调用
     * @param product product
     * @return Product
     */
    private Product getProduct(Long product) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("service-product");

        ServiceInstance serviceInstance = Optional.ofNullable(serviceInstances)
                .stream()
                .flatMap(List::stream)
                .findFirst()
                .orElse(null);

        if (serviceInstance == null) {
            log.warn("service-product 服务未找到");
            return null;
        }

        String url = String.format("http://%s:%s/product/getProduct/%s", serviceInstance.getHost(),
                serviceInstance.getPort(), product);
        return restTemplate.getForObject(url, Product.class);
    }

    /**
     * 通过客户端负载均衡方式自行拼装ip地址和端口进行跨服务的调用
     * @param product product
     * @return Product
     */
    private Product getProductByLoadBalance(Long product) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("service-product");

        if (serviceInstance == null) {
            log.warn("service-product 服务未找到");
            return null;
        }

        String url = String.format("http://%s:%s/product/getProduct/%s", serviceInstance.getHost(),
                serviceInstance.getPort(), product);
        return restTemplate.getForObject(url, Product.class);
    }

    /**
     * 通过注解均衡方式自行拼装ip地址和端口进行跨服务的调用
     * @param product product
     * @return Product
     */
    private Product getProductByLoadBalanceAnnotation(Long product) {

        String url = String.format("http://service-product/product/getProduct/%s", product);
        return restTemplate.getForObject(url, Product.class);
    }

    private Product getProductByFeignClient(Long product) {
        return productFeignClient.getProduct( product);
    }

    private Product createProductByFeignClient(Product product) {
        return productFeignClient.createProduct(product);
    }
}
