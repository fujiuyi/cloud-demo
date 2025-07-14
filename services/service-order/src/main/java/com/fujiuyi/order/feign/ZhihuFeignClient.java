package com.fujiuyi.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://www.zhihu.com", name = "zhihu")
public interface ZhihuFeignClient {

    @GetMapping("/api/v3/feed/topstory/hot-lists/total")
    String getHotList(@RequestHeader("Cookie") String cookie,
            @RequestHeader("User-Agent") String userAgent,
            @RequestParam("limit") String limit,
            @RequestParam("desktop") String desktop);
}
