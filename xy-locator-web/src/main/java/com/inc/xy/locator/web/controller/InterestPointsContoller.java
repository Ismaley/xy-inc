package com.inc.xy.locator.web.controller;

import com.inc.xy.locator.api.InterestPointsApi;
import com.inc.xy.locator.api.to.InterestPointTO;
import com.inc.xy.locator.service.InterestPointsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InterestPointsContoller implements InterestPointsApi {

    private InterestPointsService interestPointsService;
    private ModelMapper modelMapper;

    @Autowired
    public InterestPointsContoller(InterestPointsService interestPointsService, ModelMapper modelMapper) {
        this.interestPointsService = interestPointsService;
        this.modelMapper = modelMapper;
    }

    public InterestPointTO create(InterestPointTO interestPoint) {
        return null;
    }

    public List<InterestPointTO> findAll() {
        return null;
    }

    public List<InterestPointTO> findByProximity(Float xCoordinate, Float yCoordinate, Float radius) {
        return null;
    }
}
