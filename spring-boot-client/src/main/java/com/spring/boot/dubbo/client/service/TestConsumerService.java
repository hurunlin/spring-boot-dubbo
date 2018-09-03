package com.spring.boot.dubbo.client.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.spring.boot.dubbo.dto.CityDto;
import com.spring.boot.dubbo.remote.CityDubboService;
import org.springframework.stereotype.Component;

/**
 * 城市 Dubbo 服务消费者
 * <p>
 * Created by bysocket on 28/02/2017.
 */
@Component
public class TestConsumerService {

    @Reference(version = "1.0.0")
    private CityDubboService cityDubboService;

    public CityDto printCity() {
        String cityName = "温岭";
        CityDto city = cityDubboService.findCityByName(cityName);
        return city;
    }
}
