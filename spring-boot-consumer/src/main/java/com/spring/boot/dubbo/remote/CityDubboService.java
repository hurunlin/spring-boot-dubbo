package com.spring.boot.dubbo.remote;


import com.spring.boot.dubbo.dto.CityDto;

/**
 * 城市业务 Dubbo 服务层
 * <p>
 * Created by bysocket on 28/02/2017.
 */

public interface CityDubboService {

    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName
     */
    CityDto findCityByName(String cityName);
}
