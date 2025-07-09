package org.fujiuyi.order;

import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@SpringBootTest
public class DiscoveryTest {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    NacosServiceDiscovery nacosServiceDiscovery;

    @Test
    public void testDiscovery() {
        for (String service : discoveryClient.getServices()) {

            System.out.println("service name :" + service);

            for (ServiceInstance instance : discoveryClient.getInstances(service)) {
                System.out.println("instance ip :" + instance.getHost() + " port :" + instance.getPort());
            }
        }
    }

    @Test
    public void testNacosDiscovery() throws Exception {
        for (String service : nacosServiceDiscovery.getServices()) {

            System.out.println("service name :" + service);
            for (ServiceInstance instance : nacosServiceDiscovery.getInstances(service)) {
                System.out.println("instance ip :" + instance.getHost() + " port :" + instance.getPort());
            }
        }
    }
}
