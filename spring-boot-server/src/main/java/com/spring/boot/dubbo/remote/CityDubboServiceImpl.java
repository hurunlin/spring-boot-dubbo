package com.spring.boot.dubbo.remote;

import com.alibaba.dubbo.config.annotation.Service;
import com.spring.boot.dubbo.dto.CityDto;
import com.spring.boot.dubbo.entity.City;
import com.spring.boot.dubbo.service.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

// 注册为 Dubbo 服务
@Service(version = "1.0.0")
public class CityDubboServiceImpl implements CityDubboService {

    @Autowired
    private CityService cityService;

    @Override
    public CityDto findCityByName(String cityName) {
        City city = cityService.findCityById(1L);
        if (city == null) return null;
        CityDto cityDto = new CityDto();
        BeanUtils.copyProperties(city, cityDto);
        return cityDto;
    }
}
