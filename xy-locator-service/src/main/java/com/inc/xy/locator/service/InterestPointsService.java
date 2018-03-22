package com.inc.xy.locator.service;

import com.inc.xy.locator.model.InterestPoint;
import com.inc.xy.locator.model.PointSearchParam;

import java.util.List;

public interface InterestPointsService {

    InterestPoint create(InterestPoint interestPoint);

    List<InterestPoint> findAll();

    List<InterestPoint> findByProximity(PointSearchParam param);

}
