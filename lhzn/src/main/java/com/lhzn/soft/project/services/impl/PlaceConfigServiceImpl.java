package com.lhzn.soft.project.services.impl;

import com.lhzn.soft.project.mapper.PlaceConfigMapper;
import com.lhzn.soft.project.services.PlaceConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class PlaceConfigServiceImpl implements PlaceConfigService {

    @Resource
    private PlaceConfigMapper placeConfigMapper;
    @Override
    public String getIpAdders(String locationId) {
        return placeConfigMapper.getIpAddersByPlaceId(locationId);
    }

}
