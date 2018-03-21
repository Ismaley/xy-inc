package com.inc.xy.locator.service;

import com.inc.xy.locator.model.InterestPoint;

import java.util.List;

public interface InterestPointsService {

    InterestPoint create(InterestPoint interestPoint);

    List<InterestPoint> findAll();

    List<InterestPoint> findByProximity(Float xCoordinate, Float yCoordinate, Float radius);

}
