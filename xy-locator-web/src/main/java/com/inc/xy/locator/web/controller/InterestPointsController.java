package com.inc.xy.locator.web.controller;

import com.inc.xy.locator.api.InterestPointsApi;
import com.inc.xy.locator.api.to.InterestPointTO;
import com.inc.xy.locator.api.to.PointSearchParamTO;
import com.inc.xy.locator.service.InterestPointsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InterestPointsController implements InterestPointsApi {

    private InterestPointsService interestPointsService;
    private ModelMapper modelMapper;

    @Autowired
    public InterestPointsController(InterestPointsService interestPointsService, ModelMapper modelMapper) {
        this.interestPointsService = interestPointsService;
        this.modelMapper = modelMapper;
    }

    @Override
    public InterestPointTO create(InterestPointTO interestPoint) {
        return null;
    }

    @Override
    public List<InterestPointTO> findAll() {
        return null;
    }

    @Override
    public List<InterestPointTO> findByProximity(PointSearchParamTO param) {
        return null;
    }
}
