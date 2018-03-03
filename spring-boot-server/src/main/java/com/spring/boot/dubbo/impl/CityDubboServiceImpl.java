package com.spring.boot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.spring.boot.dubbo.domain.City;
import com.spring.boot.dubbo.interfaces.CityDubboService;
import com.spring.boot.dubbo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;

// 注册为 Dubbo 服务
@Service(version = "1.0.0")
public class CityDubboServiceImpl implements CityDubboService {

    @Autowired
    private CityService cityService;

    @Override
    public City findCityByName(String cityName) {
        return cityService.findCityById(1L);
    }
}
