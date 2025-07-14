package com.fujiuyi.order;

import com.fujiuyi.order.feign.ZhihuFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ZhihuFeignClientTest {

    @Autowired
    ZhihuFeignClient zhihuFeignClient;

    @Test
    public void getHotList() {
        String cookie = "_xsrf=7utTjamvWsPhbo0koWhYZOA4AxdPdRum; _zap=0e3e264f-7cee-4e23-b0b0-34350abae096; d_c0=5LMT5hkWphqPTmYcBMKVeBejSf6d44a2c8Q=|1750602463; z_c0=2|1:0|10:1750602465|4:z_c0|80:MS4xNDNxckRnQUFBQUFtQUFBQVlBSlZUZUJnUlduQ1JhZHBTVTZTOWY4dllRc3Zhb1RNNjJpTDl3PT0=|8a46e038a30f918fbadc3df3288c06c05182ccc112d13a20a234f5bc94d3e669; __zse_ck=004_25FKSkXMX1=Mti4cub=Ojb3AbKJUlQYqGQKyyKQREG43n/jVaLb3=4VvRpbrLJf707rdPHnSceZWQjO4wIsMxhdumKBq8yEVqsImhMCMbaXcktY=ndOAdE4oBsm8XtIh-kKCgvm8J9ipGinRQ3LdS456pRPaeT2igTTEbocGgZgqJHcxUVYk1pHPUZpFvtLfNEvNxvzuR6q37WVJSnffq5p3vQUOhiHRRVvKtnbwHS1QROhvtjcaYU6L5a0HtM29A; Hm_lvt_98beee57fd2ef70ccdd5ca52b9740c49=1750602465,1750684290,1751028074; BEC=b7b0f394f3fd074c6bdd2ebbdd598b4e";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36";
        String hotList = zhihuFeignClient.getHotList(cookie, userAgent, "10", "true");
        System.out.println(hotList);
    }
}
